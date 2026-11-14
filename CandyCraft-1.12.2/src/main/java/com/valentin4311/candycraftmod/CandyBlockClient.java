package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/** Client-only registrations for the blocks: variant item models, fluid state mappers, tints and tile entity renderers. */
@Mod.EventBusSubscriber(modid = "candycraftmod", value = Side.CLIENT)
public class CandyBlockClient {
   private static void variants(Block block, int fromMeta, int toMeta) {
      Item item = Item.getItemFromBlock(block);
      if (item == null || item == net.minecraft.init.Items.AIR) {
         return;
      }

      String name = block.getRegistryName().toString();
      for (int m = fromMeta; m <= toMeta; ++m) {
         ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(name + "_" + m, "inventory"));
      }
   }

   private static void base(Block block, int meta) {
      Item item = Item.getItemFromBlock(block);
      if (item != null && item != net.minecraft.init.Items.AIR) {
         ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
      }
   }

   private static void fluid(Block block) {
      final ModelResourceLocation loc = new ModelResourceLocation(block.getRegistryName(), "fluid");
      ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
         @Override
         protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return loc;
         }
      });
   }

   @SubscribeEvent
   public static void registerModels(ModelRegistryEvent event) {
      CandyBlockRegistry.init();
      // Every block item needs an explicit inventory model at meta 0 (1.12 does not fall back to the registry name).
      for (Block registered : CandyBlockRegistry.getBlocks()) {
         if (registered.getRegistryName() != null) {
            base(registered, 0);
         }
      }
      variants(CandyCraft.CandyPlanks, 1, 3);
      variants(CandyCraft.CandyLog, 1, 3);
      variants(CandyCraft.CandyLeave, 1, 3);
      variants(CandyCraft.CandySapling, 1, 3);
      variants(CandyCraft.TallCandyGrass, 1, 3);
      variants(CandyCraft.IceCream, 1, 3);
      base(CandyCraft.NougatHead, 1);
      base(CandyCraft.SugarFurnace, 3);
      fluid(CandyCraft.GrenadineFlow);
      fluid(CandyCraft.GrenadineStatic);
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCandyChest.class, new TileEntityCandyChestRenderer());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemy.class, new TileEntityAlchemyRenderer());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeleporter.class, new TileEntityRendererTeleporter());
      // Inventory/hand rendering of the chest item uses the real chest model instead of a block model.
      Item chestItem = Item.getItemFromBlock(CandyCraft.CandyChest);
      if (chestItem != null && chestItem != net.minecraft.init.Items.AIR) {
         chestItem.setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
            private final TileEntityCandyChest chest = new TileEntityCandyChest();

            @Override
            public void renderByItem(ItemStack stack, float partialTicks) {
               TileEntityRendererDispatcher.instance.render(this.chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
            }
         });
      }
   }

   @SubscribeEvent
   public static void registerBlockColors(ColorHandlerEvent.Block event) {
      CandyBlockRegistry.init();
      IBlockColor grass = new IBlockColor() {
         @Override
         public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
            if (world != null && pos != null) {
               return BiomeColorHelper.getGrassColorAtPos(world, pos);
            }

            return 16764125;
         }
      };
      event.getBlockColors().registerBlockColorHandler(grass, CandyCraft.PuddingBlock, CandyCraft.CandyLeave2);
   }

   @SubscribeEvent
   public static void registerItemColors(ColorHandlerEvent.Item event) {
      CandyBlockRegistry.init();
      event.getItemColors().registerItemColorHandler(new IItemColor() {
         @Override
         public int colorMultiplier(ItemStack stack, int tintIndex) {
            return stack.getItem() == Item.getItemFromBlock(CandyCraft.CandyLeave2) ? 11579647 : 16764125;
         }
      }, CandyCraft.PuddingBlock, CandyCraft.CandyLeave2);
   }
}
