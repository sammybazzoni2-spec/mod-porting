package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/** Helpers that keep the 1.7.10 (x, y, z, block, meta) style world-gen code readable in 1.12.2. */
public class CandyGenUtil {
   public static Block getBlock(World w, int x, int y, int z) {
      return w.getBlockState(new BlockPos(x, y, z)).getBlock();
   }

   public static IBlockState getState(World w, int x, int y, int z) {
      return w.getBlockState(new BlockPos(x, y, z));
   }

   public static int getMeta(World w, int x, int y, int z) {
      IBlockState s = w.getBlockState(new BlockPos(x, y, z));
      return s.getBlock().getMetaFromState(s);
   }

   public static IBlockState stateOf(Block b, int meta) {
      if (b == null) {
         b = Blocks.AIR;
      }
      try {
         return b.getStateFromMeta(meta);
      } catch (Exception e) {
         return b.getDefaultState();
      }
   }

   public static boolean setBlock(World w, int x, int y, int z, Block b, int meta, int flags) {
      return w.setBlockState(new BlockPos(x, y, z), stateOf(b, meta), flags);
   }

   public static boolean setBlock(World w, int x, int y, int z, Block b) {
      return setBlock(w, x, y, z, b, 0, 3);
   }

   public static boolean setAir(World w, int x, int y, int z) {
      return w.setBlockToAir(new BlockPos(x, y, z));
   }

   public static boolean isAir(World w, int x, int y, int z) {
      return w.isAirBlock(new BlockPos(x, y, z));
   }

   public static TileEntity getTile(World w, int x, int y, int z) {
      return w.getTileEntity(new BlockPos(x, y, z));
   }

   public static boolean canPlace(Block b, World w, int x, int y, int z) {
      return b.canPlaceBlockAt(w, new BlockPos(x, y, z));
   }

   public static boolean canStay(Block b, World w, int x, int y, int z) {
      return b.canPlaceBlockAt(w, new BlockPos(x, y, z));
   }

   public static Biome biome(World w, int x, int z) {
      return w.getBiome(new BlockPos(x, 0, z));
   }

   public static Material mat(Block b) {
      return b.getDefaultState().getMaterial();
   }

   public static Material mat(World w, int x, int y, int z) {
      return w.getBlockState(new BlockPos(x, y, z)).getMaterial();
   }

   /** Sets the entity of a mob spawner from a legacy 1.7.10 entity name such as "SprinterSlime" or "EntityBeetle". */
   public static void setSpawner(TileEntity te, String legacyName) {
      if (!(te instanceof net.minecraft.tileentity.TileEntityMobSpawner)) {
         return;
      }
      String want = normalize(legacyName);
      net.minecraft.util.ResourceLocation found = null;
      for (net.minecraftforge.fml.common.registry.EntityEntry e : net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValues()) {
         net.minecraft.util.ResourceLocation rl = e.getRegistryName();
         if (rl != null && "candycraftmod".equals(rl.getNamespace()) && (normalize(rl.getPath()).equals(want) || normalize(e.getName()).equals(want))) {
            found = rl;
            break;
         }
      }
      if (found == null) {
         found = new net.minecraft.util.ResourceLocation("candycraftmod", legacyName.toLowerCase());
      }
      ((net.minecraft.tileentity.TileEntityMobSpawner)te).getSpawnerBaseLogic().setEntityId(found);
   }

   private static String normalize(String n) {
      if (n == null) {
         return "";
      }
      String s = n.toLowerCase().replace("_", "").replace(".", "").replace("candycraftmod:", "");
      if (s.startsWith("entity")) {
         s = s.substring(6);
      }
      return s;
   }

   /** Replacement for the removed WeightedRandomChestContent. */
   public static class ChestContent {
      public final net.minecraft.item.ItemStack stack;
      public final int min;
      public final int max;
      public final int weight;

      public ChestContent(net.minecraft.item.Item item, int meta, int min, int max, int weight) {
         this(new net.minecraft.item.ItemStack(item, 1, meta), min, max, weight);
      }

      public ChestContent(net.minecraft.item.ItemStack stack, int min, int max, int weight) {
         this.stack = stack;
         this.min = min;
         this.max = max;
         this.weight = weight;
      }

      public static ChestContent[] merge(ChestContent[] a, ChestContent... b) {
         ChestContent[] r = new ChestContent[a.length + b.length];
         System.arraycopy(a, 0, r, 0, a.length);
         System.arraycopy(b, 0, r, a.length, b.length);
         return r;
      }

      public static void fill(java.util.Random rand, ChestContent[] contents, net.minecraft.inventory.IInventory inv, int count) {
         int total = 0;
         for (ChestContent c : contents) {
            total += c.weight;
         }
         for (int i = 0; i < count && total > 0; ++i) {
            int r = rand.nextInt(total);
            ChestContent pick = contents[0];
            for (ChestContent c : contents) {
               r -= c.weight;
               if (r < 0) {
                  pick = c;
                  break;
               }
            }
            net.minecraft.item.ItemStack st = pick.stack.copy();
            if (st.isEmpty()) {
               continue;
            }
            st.setCount(pick.min + rand.nextInt(pick.max - pick.min + 1));
            if (st.getCount() > st.getMaxStackSize()) {
               st.setCount(st.getMaxStackSize());
            }
            inv.setInventorySlotContents(rand.nextInt(inv.getSizeInventory()), st);
         }
      }
   }

   public static net.minecraft.item.ItemStack randomBook(java.util.Random rand) {
      java.util.List<net.minecraft.enchantment.Enchantment> list = new java.util.ArrayList<net.minecraft.enchantment.Enchantment>();
      for (net.minecraft.enchantment.Enchantment e : net.minecraft.enchantment.Enchantment.REGISTRY) {
         list.add(e);
      }
      net.minecraft.enchantment.Enchantment e = list.get(rand.nextInt(list.size()));
      int lvl = e.getMinLevel() + rand.nextInt(e.getMaxLevel() - e.getMinLevel() + 1);
      return net.minecraft.item.ItemEnchantedBook.getEnchantedItemStack(new net.minecraft.enchantment.EnchantmentData(e, lvl));
   }

   public static boolean hasItem(net.minecraft.entity.player.EntityPlayer player, net.minecraft.item.Item item) {
      for (net.minecraft.item.ItemStack st : player.inventory.mainInventory) {
         if (!st.isEmpty() && st.getItem() == item) {
            return true;
         }
      }
      return false;
   }
}
