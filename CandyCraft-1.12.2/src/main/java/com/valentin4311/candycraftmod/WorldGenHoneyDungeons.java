package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHoneyDungeons extends WorldGenerator {
   public static final CandyGenUtil.ChestContent[] field_111189_aa;

   @Override
   public boolean generate(World par1World, Random par2Random, BlockPos pos) {
      int par3 = pos.getX();
      int par4 = pos.getY();
      int par5 = pos.getZ();
      byte b0 = 3;
      int l = par2Random.nextInt(2) + 2;
      int i1 = par2Random.nextInt(2) + 2;
      int j1 = 0;

      for(int k1 = par3 - l - 1; k1 <= par3 + l + 1; ++k1) {
         for(int l1 = par4 - 1; l1 <= par4 + b0 + 1; ++l1) {
            for(int i2 = par5 - i1 - 1; i2 <= par5 + i1 + 1; ++i2) {
               Material material = CandyGenUtil.mat(par1World, k1, l1, i2);
               if (l1 == par4 - 1 && !material.isSolid()) {
                  return false;
               }

               if (l1 == par4 + b0 + 1 && !material.isSolid()) {
                  return false;
               }

               if ((k1 == par3 - l - 1 || k1 == par3 + l + 1 || i2 == par5 - i1 - 1 || i2 == par5 + i1 + 1) && l1 == par4 && CandyGenUtil.isAir(par1World, k1, l1, i2) && CandyGenUtil.isAir(par1World, k1, l1 + 1, i2)) {
                  ++j1;
               }
            }
         }
      }

      if (j1 >= 1 && j1 <= 5) {
         for(int var17 = par3 - l - 1; var17 <= par3 + l + 1; ++var17) {
            for(int l1 = par4 + b0; l1 >= par4 - 1; --l1) {
               for(int i2 = par5 - i1 - 1; i2 <= par5 + i1 + 1; ++i2) {
                  if (var17 != par3 - l - 1 && l1 != par4 - 1 && i2 != par5 - i1 - 1 && var17 != par3 + l + 1 && l1 != par4 + b0 + 1 && i2 != par5 + i1 + 1) {
                     CandyGenUtil.setAir(par1World, var17, l1, i2);
                  } else if (l1 >= 0 && !CandyGenUtil.mat(par1World, var17, l1 - 1, i2).isSolid()) {
                     CandyGenUtil.setAir(par1World, var17, l1, i2);
                  } else if (CandyGenUtil.mat(par1World, var17, l1, i2).isSolid()) {
                     if (l1 == par4 - 1 && par2Random.nextInt(4) != 0) {
                        CandyGenUtil.setBlock(par1World, var17, l1, i2, CandyCraft.HoneyBlock, 0, 2);
                     } else {
                        CandyGenUtil.setBlock(par1World, var17, l1, i2, CandyCraft.HoneyBlock, 0, 2);
                     }
                  }
               }
            }
         }

         for(int var18 = 0; var18 < 2; ++var18) {
            for(int l1 = 0; l1 < 3; ++l1) {
               int i2 = par3 + par2Random.nextInt(l * 2 + 1) - l;
               int j2 = par5 + par2Random.nextInt(i1 * 2 + 1) - i1;
               if (CandyGenUtil.isAir(par1World, i2, par4, j2)) {
                  int k2 = 0;
                  if (CandyGenUtil.mat(par1World, i2 - 1, par4, j2).isSolid()) {
                     ++k2;
                  }

                  if (CandyGenUtil.mat(par1World, i2 + 1, par4, j2).isSolid()) {
                     ++k2;
                  }

                  if (CandyGenUtil.mat(par1World, i2, par4, j2 - 1).isSolid()) {
                     ++k2;
                  }

                  if (CandyGenUtil.mat(par1World, i2, par4, j2 + 1).isSolid()) {
                     ++k2;
                  }

                  if (k2 == 1) {
                     CandyGenUtil.setBlock(par1World, i2, par4, j2, CandyCraft.CandyChest, 0, 2);
                     TileEntityCandyChest tileentitychest = (TileEntityCandyChest)CandyGenUtil.getTile(par1World, i2, par4, j2);
                     CandyGenUtil.ChestContent[] var16 = CandyGenUtil.ChestContent.merge(field_111189_aa, new CandyGenUtil.ChestContent(CandyGenUtil.randomBook(par2Random), 1, 1, 5));
                     if (tileentitychest != null) {
                        CandyGenUtil.ChestContent.fill(par2Random, var16, tileentitychest, 8);
                     }
                     break;
                  }
               }
            }
         }

         CandyGenUtil.setBlock(par1World, par3, par4, par5, Blocks.MOB_SPAWNER, 0, 2);
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)CandyGenUtil.getTile(par1World, par3, par4, par5);
         if (tileentitymobspawner != null) {
            CandyGenUtil.setSpawner(tileentitymobspawner, this.pickMobSpawner(par2Random));
         } else {
            System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
         }

         return true;
      } else {
         return false;
      }
   }

   private String pickMobSpawner(Random par1Random) {
      return "CaramelBee";
   }

   static {
      field_111189_aa = new CandyGenUtil.ChestContent[]{new CandyGenUtil.ChestContent(CandyCraft.PEZ, 0, 2, 8, 10), new CandyGenUtil.ChestContent(Items.SADDLE, 0, 1, 1, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneyEmblem, 0, 1, 1, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneyComb, 0, 1, 8, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneyHelmet, 0, 1, 1, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneySword, 0, 1, 1, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneyShovel, 0, 1, 1, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneyComb, 0, 1, 4, 10), new CandyGenUtil.ChestContent(CandyCraft.Licorice, 0, 1, 4, 10), new CandyGenUtil.ChestContent(CandyCraft.Lollipop, 0, 1, 15, 10), new CandyGenUtil.ChestContent(Item.getItemFromBlock(CandyCraft.JellyOre), 0, 1, 4, 10), new CandyGenUtil.ChestContent(CandyCraft.BarleySugar, 0, 1, 8, 10), new CandyGenUtil.ChestContent(CandyCraft.HoneyShard, 0, 1, 4, 10), new CandyGenUtil.ChestContent(CandyCraft.CaramelBucket, 0, 1, 1, 10), new CandyGenUtil.ChestContent(CandyCraft.LicoricePlate, 0, 1, 1, 1), new CandyGenUtil.ChestContent(Items.DIAMOND, 0, 1, 4, 6), new CandyGenUtil.ChestContent(Item.getItemFromBlock(CandyCraft.HoneyTorch), 0, 1, 32, 10)};
   }
}
