package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import net.minecraft.init.Enchantments;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCandyHouse extends WorldGenerator {
   public static Item[] itemToStock;

   @Override
   public boolean generate(World par1World, Random par2Random, BlockPos pos) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      WorldProviderCandy.canGenHouses = 300;
      if (par2Random.nextInt(3) != 0) {
         return false;
      } else {
         par3 -= 8;

         for(par5 -= 8; par4 > 5 && CandyGenUtil.isAir(par1World, par3, par4, par5); --par4) {
         }

         if (CandyGenUtil.getBlock(par1World, par3, par4 - 1, par5) != CandyCraft.PuddingBlock) {
            return false;
         } else {
            for(int iGen = 0; iGen < 5; ++iGen) {
               for(int xGen = 0; xGen < 5; ++xGen) {
                  for(int zGen = 0; zGen < 5; ++zGen) {
                     this.setBlockAndMetadata(par1World, par3 - 2 + xGen, iGen + par4, par5 - 2 + zGen, (Block)null, 0);
                  }
               }
            }

            for(int genLayer = -1; genLayer <= 4; ++genLayer) {
               if (genLayer == -1 || genLayer == 3) {
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5, CandyCraft.CandyLog, 8);
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5 - 1, CandyCraft.CandyLog, 8);
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5 + 1, CandyCraft.CandyLog, 8);
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5, CandyCraft.CandyLog, 8);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5 - 1, CandyCraft.CandyLog, 8);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5 + 1, CandyCraft.CandyLog, 8);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 - 1, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 4);
                  this.setBlockAndMetadata(par1World, par3, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 4);
                  this.setBlockAndMetadata(par1World, par3 + 1, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 4);
                  this.setBlockAndMetadata(par1World, par3 - 1, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 4);
                  this.setBlockAndMetadata(par1World, par3, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 4);
                  this.setBlockAndMetadata(par1World, par3 + 1, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 4);
                  if (genLayer == -1) {
                     this.setBlockAndMetadata(par1World, par3 + 1, par4 + genLayer, par5, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3 - 1, par4 + genLayer, par5, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3, par4 + genLayer, par5 - 1, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3, par4 + genLayer, par5 + 1, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3 + 1, par4 + genLayer, par5 + 1, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3 - 1, par4 + genLayer, par5 + 1, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3 + 1, par4 + genLayer, par5 - 1, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3 - 1, par4 + genLayer, par5 - 1, CandyCraft.CandyPlanks, 0);
                     this.setBlockAndMetadata(par1World, par3, par4 + genLayer, par5, CandyCraft.CandyPlanks, 0);
                  }
               }

               if (genLayer != 0 && genLayer != 2) {
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 - 2, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5 + 2, CandyCraft.CandyLog, 0);
                  this.setBlockAndMetadata(par1World, par3 + 2, par4 + genLayer, par5 - 2, CandyCraft.CandyLog, 0);
               } else {
                  CandyGenUtil.setBlock(par1World, par3 - 2, par4 + genLayer, par5 - 2, CandyCraft.BarleyWall, 0, 3);
                  CandyGenUtil.setBlock(par1World, par3 - 2, par4 + genLayer, par5 + 2, CandyCraft.BarleyWall, 0, 3);
                  CandyGenUtil.setBlock(par1World, par3 + 2, par4 + genLayer, par5 + 2, CandyCraft.BarleyWall, 0, 3);
                  CandyGenUtil.setBlock(par1World, par3 + 2, par4 + genLayer, par5 - 2, CandyCraft.BarleyWall, 0, 3);
               }
            }

            this.setBlockAndMetadata(par1World, par3 - 2, par4, par5, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4, par5, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3, par4, par5 + 2, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3, par4, par5 - 2, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 1, par5 + 1, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 1, par5 - 1, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 1, par5 + 2, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 1, par5 - 2, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 1, par5 - 1, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 1, par5 + 1, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 1, par5 + 2, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 1, par5 - 2, CandyCraft.BarleyBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4, par5 + 1, CandyCraft.BarleyBlock, 9);
            this.setBlockAndMetadata(par1World, par3 + 2, par4, par5 - 1, CandyCraft.BarleyBlock, 9);
            this.setBlockAndMetadata(par1World, par3 + 1, par4, par5 + 2, CandyCraft.BarleyBlock, 7);
            this.setBlockAndMetadata(par1World, par3 - 1, par4, par5 - 2, CandyCraft.BarleyBlock, 7);
            this.setBlockAndMetadata(par1World, par3 - 2, par4, par5 - 1, CandyCraft.BarleyBlock, 9);
            this.setBlockAndMetadata(par1World, par3 + 2, par4, par5 + 1, CandyCraft.BarleyBlock, 9);
            this.setBlockAndMetadata(par1World, par3 - 1, par4, par5 + 2, CandyCraft.BarleyBlock, 7);
            this.setBlockAndMetadata(par1World, par3 + 1, par4, par5 - 2, CandyCraft.BarleyBlock, 7);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 1, par5, CandyCraft.BarleyBlock, 9);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 1, par5, CandyCraft.BarleyBlock, 9);
            this.setBlockAndMetadata(par1World, par3, par4 + 1, par5 + 2, CandyCraft.BarleyBlock, 7);
            this.setBlockAndMetadata(par1World, par3, par4 + 1, par5 - 2, CandyCraft.BarleyBlock, 7);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 2, par5, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 2, par5 + 1, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 2, par5 - 1, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 2, par5, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 2, par5 + 1, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 2, par5 - 1, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3, par4 + 2, par5 + 2, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 2, par5 + 2, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 2, par5 + 2, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3, par4 + 2, par5 - 2, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 2, par5 - 2, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 2, par5 - 2, CandyCraft.CaramelBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 3, par5, CandyCraft.CandyHalfStep, 11);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 3, par5, CandyCraft.CandyHalfStep, 11);
            this.setBlockAndMetadata(par1World, par3, par4 + 3, par5 - 1, CandyCraft.CandyHalfStep, 11);
            this.setBlockAndMetadata(par1World, par3, par4 + 3, par5 + 1, CandyCraft.CandyHalfStep, 11);
            this.setBlockAndMetadata(par1World, par3, par4 + 4, par5 + 2, CandyCraft.CandyHalfStep, 0);
            this.setBlockAndMetadata(par1World, par3, par4 + 4, par5 - 2, CandyCraft.CandyHalfStep, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 + 4, par5, CandyCraft.CandyHalfStep, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 + 4, par5, CandyCraft.CandyHalfStep, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 3, par5 + 1, CandyCraft.CandyPlanks, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 3, par5 + 1, CandyCraft.CandyPlanks, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 + 3, par5 - 1, CandyCraft.CandyPlanks, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 + 3, par5 - 1, CandyCraft.CandyPlanks, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 - 2, par5, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 - 2, par5 - 1, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 - 2, par5 - 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 - 2, par5 + 1, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 2, par4 - 2, par5 + 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 - 2, par5, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 - 2, par5 - 1, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 - 2, par5 - 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 - 2, par5 + 1, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 2, par4 - 2, par5 + 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 - 2, par5 - 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3, par4 - 2, par5 - 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 - 2, par5 - 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 - 1, par4 - 2, par5 + 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3, par4 - 2, par5 + 2, CandyCraft.FlourBlock, 0);
            this.setBlockAndMetadata(par1World, par3 + 1, par4 - 2, par5 + 2, CandyCraft.FlourBlock, 0);
            CandyGenUtil.setBlock(par1World, par3, par4, par5, CandyCraft.CandyChest, 0, 3);
            TileEntityCandyChest chest = (TileEntityCandyChest)CandyGenUtil.getTile(par1World, par3, par4, par5);
            int time = par2Random.nextInt(7) + 5;

            for(int chestTime = 0; chestTime <= time; ++chestTime) {
               Item it = itemToStock[par2Random.nextInt(itemToStock.length)];
               int stack = par2Random.nextInt(7) + 4;
               ItemStack st = new ItemStack(it);
               st.setCount(stack > st.getMaxStackSize() ? st.getMaxStackSize() : stack);
               if (par2Random.nextInt(15) != 12) {
                  chest.setInventorySlotContents(par2Random.nextInt(27), new ItemStack(st.getItem(), st.getCount()));
               } else {
                  int arm = par2Random.nextInt(4);
                  ItemStack helmet = new ItemStack(CandyCraft.LicoriceHelmet);
                  ItemStack plate = new ItemStack(CandyCraft.LicoricePlate);
                  ItemStack leggings = new ItemStack(CandyCraft.LicoriceLeggings);
                  ItemStack boots = new ItemStack(CandyCraft.LicoriceBoots);
                  if (arm == 0) {
                     helmet.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                     if (par2Random.nextInt(5) != 0) {
                        helmet.addEnchantment(Enchantments.FIRE_PROTECTION, 1);
                     }

                     chest.setInventorySlotContents(par2Random.nextInt(27), helmet);
                  }

                  if (arm == 1) {
                     plate.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                     if (par2Random.nextInt(5) != 0) {
                        plate.addEnchantment(Enchantments.FIRE_PROTECTION, 1);
                     }

                     chest.setInventorySlotContents(par2Random.nextInt(27), plate);
                  }

                  if (arm == 2) {
                     leggings.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                     if (par2Random.nextInt(5) != 0) {
                        leggings.addEnchantment(Enchantments.FIRE_PROTECTION, 1);
                     }

                     chest.setInventorySlotContents(par2Random.nextInt(27), leggings);
                  }

                  if (arm == 3) {
                     boots.addEnchantment(Enchantments.PROTECTION, par2Random.nextInt(3) + 2);
                     if (par2Random.nextInt(5) != 0) {
                        boots.addEnchantment(Enchantments.FIRE_PROTECTION, 1);
                     }

                     chest.setInventorySlotContents(par2Random.nextInt(27), boots);
                  }
               }
            }

            chest.setInventorySlotContents(0, new ItemStack(Items.LAVA_BUCKET));
            chest.setInventorySlotContents(1, new ItemStack(CandyCraft.SugarBlock, 12, 0));
            CandyGenUtil.setBlock(par1World, par3, par4 - 1, par5, CandyCraft.HoneyLamp, 0, 3);
            return true;
         }
      }
   }

   public void setBlockAndMetadata(World world, int x, int y, int z, Block bl, int m) {
      if (bl != null) {
         CandyGenUtil.setBlock(world, x, y, z, bl, m, 3);
      } else {
         CandyGenUtil.setAir(world, x, y, z);
      }

   }

   public void setBlock(World world, int x, int y, int z, Block bl) {
      if (bl != null) {
         CandyGenUtil.setBlock(world, x, y, z, bl, 0, 3);
      } else {
         CandyGenUtil.setAir(world, x, y, z);
      }

   }

   public void setBlock(World world, int x, int y, int z, Block bl, int i, int j) {
      if (bl != null) {
         CandyGenUtil.setBlock(world, x, y, z, bl, i, j);
      } else {
         CandyGenUtil.setAir(world, x, y, z);
      }

   }

   static {
      itemToStock = new Item[]{Item.getItemFromBlock(CandyCraft.PuddingBlock), CandyCraft.LollipopSeeds, Item.getItemFromBlock(CandyCraft.BarleyBlock), CandyCraft.Lollipop, CandyCraft.BarleySugar, CandyCraft.Licorice, Item.getItemFromBlock(CandyCraft.JellyJump), Item.getItemFromBlock(CandyCraft.JellyJumpRelative), CandyCraft.HoneyEmblem, CandyCraft.HoneyEmblem, CandyCraft.HoneyEmblem, CandyCraft.CottonCandy, CandyCraft.WaffleNugget, CandyCraft.PEZDust, CandyCraft.MarshmallowFlower, CandyCraft.GlueDynamite, CandyCraft.Dynamite, CandyCraft.Fork, CandyCraft.CranberryFish, CandyCraft.CranberryFishCooked, Item.getItemFromBlock(CandyCraft.PoisonousFlower), CandyCraft.PEZ};
   }
}
