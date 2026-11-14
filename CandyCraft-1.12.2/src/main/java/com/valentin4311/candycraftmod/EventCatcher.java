package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

/** Common (both sides / server) event handling. Client-only handling is in ClientEventCatcher. */
public class EventCatcher {
   @SubscribeEvent
   public void onWorldTick(TickEvent.WorldTickEvent event) {
      if (event.side == Side.SERVER && event.phase == Phase.START && CandyCraft.serverTicker != null) {
         CandyCraft.serverTicker.onWorldTick(event.world);
      }
   }

   @SubscribeEvent
   public void onPlayerTick(TickEvent.PlayerTickEvent event) {
      if (event.side == Side.SERVER && event.phase == Phase.END && CandyCraft.serverTicker != null) {
         CandyCraft.serverTicker.onPlayerTick(event.player);
      }
   }

   @SubscribeEvent
   public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
      if (event.getEntity() != null && event.getEntity().world.provider.getDimension() == CandyCraft.idDimension2) {
         event.setCanceled(true);
      }
   }

   @SubscribeEvent
   public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
      if (event.getEntity() != null && event.getEntity().world.provider.getDimension() == CandyCraft.idDimension2) {
         event.setCanceled(true);
      }
   }

   @SubscribeEvent
   public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
      World w = event.getWorld();
      if (w.provider.getDimension() == CandyCraft.idDimension2) {
         Block b = w.getBlockState(event.getPos()).getBlock();
         if (b != Blocks.LEVER && b != CandyCraft.JellySentryKeyHole && b != CandyCraft.JellyBossKeyHole && b != CandyCraft.BlockTeleporter && b != CandyCraft.CandyChest) {
            event.setCanceled(true);
         }
      }
   }

   @SubscribeEvent
   public void onEntityHurt2(LivingHurtEvent event) {
      if (event.getEntity() instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)event.getEntity();
         if (event.getSource() == DamageSource.FALL) {
            if (player.dimension == CandyCraft.idDimension2) {
               event.setAmount(0.0F);
               event.setCanceled(true);
               return;
            }
            if (hasItem(player, CandyCraft.JellyEmblem)) {
               event.setAmount(event.getAmount() * 0.7F);
            }
            ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if (!boots.isEmpty() && boots.getItem() == CandyCraft.FallBoots) {
               event.setAmount(0.0F);
               event.setCanceled(true);
               return;
            }
         }
         if (event.getSource().getTrueSource() instanceof EntityArrow && hasItem(player, CandyCraft.SuguardEmblem)) {
            event.setAmount(event.getAmount() * 0.8F);
         }
      }
   }

   private static boolean hasItem(EntityPlayer player, net.minecraft.item.Item item) {
      for (ItemStack s : player.inventory.mainInventory) {
         if (!s.isEmpty() && s.getItem() == item) {
            return true;
         }
      }
      return false;
   }

   @SubscribeEvent
   public void onFill(FillBucketEvent event) {
      RayTraceResult target = event.getTarget();
      if (target != null && target.typeOfHit == RayTraceResult.Type.BLOCK) {
         BlockPos pos = target.getBlockPos();
         if (event.getEntityPlayer().dimension == CandyCraft.idDimension2) {
            event.setResult(Result.DENY);
            return;
         }
         if (event.getEmptyBucket().getItem() == Items.BUCKET) {
            if (!event.getEntityPlayer().canPlayerEdit(pos, target.sideHit, event.getEmptyBucket())) {
               return;
            }
            World world = event.getWorld();
            IBlockState state = world.getBlockState(pos);
            Block b = state.getBlock();
            if ((b == CandyCraft.GrenadineFlow || b == CandyCraft.GrenadineStatic) && b.getMetaFromState(state) == 0) {
               world.setBlockToAir(pos);
               event.setFilledBucket(new ItemStack(CandyCraft.GrenadineBucket));
               event.setResult(Result.ALLOW);
            }
         }
      }
   }

   @SubscribeEvent
   public void onDeath2(LivingDeathEvent event) {
      if (!event.getEntity().world.isRemote) {
         for (int i = ServerTick.dynamiteCallBack.size() - 1; i >= 0; --i) {
            if (ServerTick.dynamiteCallBack.get(i).entity == event.getEntity()) {
               ServerTick.dynamiteCallBack.remove(i);
            }
         }
      }
   }

   @SubscribeEvent
   public void onUseBonemeal(BonemealEvent event) {
      World world = event.getWorld();
      BlockPos pos = event.getPos();
      Block block = event.getBlock().getBlock();
      if (block == CandyCraft.CandySapling && !world.isRemote) {
         if ((double)world.rand.nextFloat() < 0.45D) {
            world.playEvent(2005, pos, 0);
            ((BlockCandySapling)CandyCraft.CandySapling).markOrGrowMarked(world, pos, world.rand);
         }
         event.setResult(Result.ALLOW);
      }
      if (block == CandyCraft.LollipopPlant && !world.isRemote && block.getMetaFromState(event.getBlock()) != 7) {
         world.playEvent(2005, pos, 0);
         ((BlockLollipopPlant)CandyCraft.LollipopPlant).fertilize(world, pos);
         event.setResult(Result.ALLOW);
      }
      if (block == CandyCraft.DragiCrops && !world.isRemote && block.getMetaFromState(event.getBlock()) < 3) {
         world.playEvent(2005, pos, 0);
         world.setBlockState(pos, block.getStateFromMeta(block.getMetaFromState(event.getBlock()) + 1), 2);
         event.setResult(Result.ALLOW);
      }
      if (block == CandyCraft.PuddingBlock) {
         if (!world.isRemote) {
            this.growPlant(world, world.rand, pos);
         }
         event.setResult(Result.ALLOW);
      }
   }

   public void growPlant(World world, Random rand, BlockPos origin) {
      label34:
      for (int l = 0; l < 128; ++l) {
         BlockPos p = origin.up();
         for (int l1 = 0; l1 < l / 16; ++l1) {
            p = p.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
            if (world.getBlockState(p.down()).getBlock() != CandyCraft.PuddingBlock || world.getBlockState(p).isNormalCube()) {
               continue label34;
            }
         }
         if (world.isAirBlock(p)) {
            if (rand.nextInt(8) != 0) {
               if (CandyCraft.TallCandyGrass.canPlaceBlockAt(world, p)) {
                  world.setBlockState(p, CandyCraft.TallCandyGrass.getStateFromMeta(rand.nextInt(4)), 3);
               }
            } else {
               world.getBiome(p).plantFlower(world, rand, p);
            }
         }
      }
   }
}
