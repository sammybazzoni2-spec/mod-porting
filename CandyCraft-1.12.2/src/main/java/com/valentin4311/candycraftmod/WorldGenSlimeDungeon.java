package com.valentin4311.candycraftmod;

import net.minecraft.util.math.BlockPos;
import net.minecraft.init.Enchantments;
import net.minecraft.util.math.MathHelper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSlimeDungeon extends WorldGenerator {
   int incrementer = -2;
   int posX = 0;
   int posY = 0;
   int xb;
   int yb;
   int zb;
   int dim = 0;

   public WorldGenSlimeDungeon(int oX, int oY, int oZ, int dime) {
      this.xb = oX;
      this.yb = oY;
      this.zb = oZ;
      this.dim = dime;
   }

   public boolean spawnRoom(World world, Random rand, int x, int y, int z) {
      for(int i = 0; i < 10; ++i) {
         for(int k = 0; k < 2; ++k) {
            for(int j = 0; j < 3; ++j) {
               CandyGenUtil.setBlock(world, i + x, y + 1 + k * 4, z + j, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 10; ++i) {
         for(int k = 0; k < 3; ++k) {
            for(int j = 0; j < 2; ++j) {
               if (j != 0 || i < 8) {
                  CandyGenUtil.setBlock(world, i + x, y + 2 + k, z - 1 + j * 4, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }
            }
         }
      }

      for(int i = 0; i < 2; ++i) {
         for(int k = 0; k < 3; ++k) {
            for(int j = 0; j < 3; ++j) {
               CandyGenUtil.setBlock(world, x - 1 + i * 11, y + 2 + k, z + j, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 2; ++i) {
         for(int k = 0; k < 2; ++k) {
            for(int j = 0; j < 3; ++j) {
               CandyGenUtil.setBlock(world, i + x + 8, y + 1 + k * 4, z + j - 3, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 2; ++i) {
         for(int k = 0; k < 3; ++k) {
            for(int j = 0; j < 3; ++j) {
               CandyGenUtil.setBlock(world, x - 1 + i * 3 + 8, y + 2 + k, z + j - 3, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      CandyGenUtil.setBlock(world, x + 7, y + 2, z - 4, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + 10, y + 2, z - 4, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + 8, y + 5, z - 4, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + 9, y + 5, z - 4, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + 8, y + 3, z - 4, CandyCraft.LicoriceBrickStairs, 1, 2);
      CandyGenUtil.setBlock(world, x + 9, y + 3, z - 4, CandyCraft.LicoriceBrickStairs, 0, 2);
      CandyGenUtil.setBlock(world, x + 8, y + 4, z - 4, CandyCraft.LicoriceBrickStairs, 5, 2);
      CandyGenUtil.setBlock(world, x + 9, y + 4, z - 4, CandyCraft.LicoriceBrickStairs, 4, 2);
      CandyGenUtil.setBlock(world, x + 8, y + 2, z - 4, CandyCraft.LicoriceBrickStairs, 3, 2);
      CandyGenUtil.setBlock(world, x + 9, y + 2, z - 4, CandyCraft.LicoriceBrickStairs, 3, 2);
      CandyGenUtil.setBlock(world, x + 6, y + 3, z - 4, Blocks.STICKY_PISTON, 13, 2);
      CandyGenUtil.setBlock(world, x + 6, y + 4, z - 4, Blocks.STICKY_PISTON, 13, 2);
      CandyGenUtil.setBlock(world, x + 7, y + 3, z - 4, Blocks.PISTON_HEAD, 13, 2);
      CandyGenUtil.setBlock(world, x + 7, y + 4, z - 4, Blocks.PISTON_HEAD, 13, 2);
      CandyGenUtil.setBlock(world, x + 11, y + 3, z - 4, Blocks.STICKY_PISTON, 12, 2);
      CandyGenUtil.setBlock(world, x + 11, y + 4, z - 4, Blocks.STICKY_PISTON, 12, 2);
      CandyGenUtil.setBlock(world, x + 10, y + 3, z - 4, Blocks.PISTON_HEAD, 12, 2);
      CandyGenUtil.setBlock(world, x + 10, y + 4, z - 4, Blocks.PISTON_HEAD, 12, 2);
      this.genRedstone(world, x + 9, y, z - 4);
      this.genRedstone(world, x + 8, y, z - 4);
      this.genRedstone(world, x + 11, y, z - 4);
      this.genRedstone(world, x + 6, y, z - 4);
      this.genRedstone(world, x + 11, y, z - 3);
      this.genRedstone(world, x + 6, y, z - 3);
      this.genRedstone(world, x + 12, y + 1, z - 3);
      this.genRedstone(world, x + 5, y + 1, z - 3);
      this.genRedstone(world, x + 13, y + 1, z - 3);
      this.genRedstone(world, x + 4, y + 1, z - 3);
      this.genRedstone(world, x + 14, y + 1, z - 3);
      this.genRedstone(world, x + 3, y + 1, z - 3);
      this.genRedstone(world, x + 15, y + 1, z - 3);
      this.genRedstone(world, x + 2, y + 1, z - 3);
      CandyGenUtil.setBlock(world, x + 10, y, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 7, y, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 10, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 1, 2);
      CandyGenUtil.setBlock(world, x + 7, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 3, 2);
      this.genRedstone(world, x + 15, y + 2, z - 4);
      this.genRedstone(world, x + 2, y + 2, z - 4);
      this.genRedstone(world, x + 14, y + 2, z - 4);
      this.genRedstone(world, x + 3, y + 2, z - 4);
      CandyGenUtil.setBlock(world, x + 13, y + 3, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 4, y + 3, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 12, y + 4, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 5, y + 4, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 5, y + 3, z - 4, Blocks.REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x + 12, y + 3, z - 4, Blocks.REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x + 14, y + 3, z - 3, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 3, y + 3, z - 3, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 8, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE, 0, 3);
      CandyGenUtil.setBlock(world, x + 9, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 1, z + 1, CandyCraft.CaramelBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 2, z + 1, CandyCraft.BlockTeleporter, 0, 3);
      TileEntityTeleporter port = (TileEntityTeleporter)CandyGenUtil.getTile(world, x + 1, y + 2, z + 1);
      port.x = this.xb;
      port.y = this.yb;
      port.z = this.zb;
      port.tickExisted = 400;
      port.generated = true;
      port.dim = this.dim;
      CandyGenUtil.setBlock(world, x - 1, y + 3, z + 1, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x - 2, y + 3, z + 1, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 10, y + 3, z + 1, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 11, y + 3, z + 1, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 10, y + 3, z - 2, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 11, y + 3, z - 2, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 7, y + 3, z - 2, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 3, z - 2, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 3, z - 1, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 3, z - 2, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 3, z + 3, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 3, z + 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 2, y + 3, z - 1, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 2, y + 3, z - 2, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 2, y + 3, z + 3, CandyCraft.JellyJump, 0, 3);
      CandyGenUtil.setBlock(world, x + 2, y + 3, z + 4, CandyCraft.JawBreakerBlock, 0, 3);

      for(int i = 0; i < 3; ++i) {
         CandyGenUtil.setBlock(world, x + 6, y + 2 + i, z + 1, CandyCraft.JawBreakerBlock, 0, 3);
      }

      this.posX += 5;
      return true;
   }

   public void genRedstone(World world, int i, int j, int k) {
      CandyGenUtil.setBlock(world, i, j, k, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, i, j + 1, k, Blocks.REDSTONE_WIRE, 0, 3);
   }

   public boolean genCoridor(World world, Random rand, int x, int y, int z) {
      for(int i = 0; i < 4; ++i) {
         for(int k = 0; k < 2; ++k) {
            for(int j = 0; j < 10; ++j) {
               CandyGenUtil.setBlock(world, i - 1 + x, y + 1 + k * 4, z - 1 - j, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      boolean lamp = false;
      boolean side = false;

      for(int i = 0; i < 2; ++i) {
         for(int k = 0; k < 3; ++k) {
            for(int j = 0; j < 10; ++j) {
               if (!lamp) {
                  CandyGenUtil.setBlock(world, x - 1 + i * 3, y + 2 + k, z - 1 - j, rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               } else {
                  CandyGenUtil.setBlock(world, x - 1 + i * 3, y + 2 + k, z - 1 - j, j < 9 ? CandyCraft.JellyJumpHard : (rand.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight), 0, 3);
                  if (side) {
                     CandyGenUtil.setBlock(world, x - 1 + i * 3 + 1, y + 2 + k, z - 1 - j, CandyCraft.JawBreakerBlock, 0, 3);
                  } else {
                     CandyGenUtil.setBlock(world, x - 1 + i * 3 - 1, y + 2 + k, z - 1 - j, CandyCraft.JawBreakerBlock, 0, 3);
                  }
               }

               lamp = !lamp;
            }
         }

         side = !side;
      }

      x -= 8;
      z -= 2;
      CandyGenUtil.setBlock(world, x + 6, y + 3, z - 4, Blocks.STICKY_PISTON, 13, 2);
      CandyGenUtil.setBlock(world, x + 6, y + 4, z - 4, Blocks.STICKY_PISTON, 13, 2);
      CandyGenUtil.setBlock(world, x + 7, y + 3, z - 4, Blocks.PISTON_HEAD, 13, 2);
      CandyGenUtil.setBlock(world, x + 7, y + 4, z - 4, Blocks.PISTON_HEAD, 13, 2);
      CandyGenUtil.setBlock(world, x + 11, y + 3, z - 4, Blocks.STICKY_PISTON, 12, 2);
      CandyGenUtil.setBlock(world, x + 11, y + 4, z - 4, Blocks.STICKY_PISTON, 12, 2);
      CandyGenUtil.setBlock(world, x + 10, y + 3, z - 4, Blocks.PISTON_HEAD, 12, 2);
      CandyGenUtil.setBlock(world, x + 10, y + 4, z - 4, Blocks.PISTON_HEAD, 12, 2);
      this.genRedstone(world, x + 9, y, z - 4);
      this.genRedstone(world, x + 8, y, z - 4);
      this.genRedstone(world, x + 11, y, z - 4);
      this.genRedstone(world, x + 6, y, z - 4);
      this.genRedstone(world, x + 11, y, z - 3);
      this.genRedstone(world, x + 6, y, z - 3);
      this.genRedstone(world, x + 12, y + 1, z - 3);
      this.genRedstone(world, x + 5, y + 1, z - 3);
      this.genRedstone(world, x + 13, y + 1, z - 3);
      this.genRedstone(world, x + 4, y + 1, z - 3);
      this.genRedstone(world, x + 14, y + 1, z - 3);
      this.genRedstone(world, x + 3, y + 1, z - 3);
      this.genRedstone(world, x + 15, y + 1, z - 3);
      this.genRedstone(world, x + 2, y + 1, z - 3);
      CandyGenUtil.setBlock(world, x + 10, y, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 7, y, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 10, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 1, 2);
      CandyGenUtil.setBlock(world, x + 7, y + 1, z - 4, Blocks.UNPOWERED_REPEATER, 3, 2);
      this.genRedstone(world, x + 15, y + 2, z - 4);
      this.genRedstone(world, x + 2, y + 2, z - 4);
      this.genRedstone(world, x + 14, y + 2, z - 4);
      this.genRedstone(world, x + 3, y + 2, z - 4);
      CandyGenUtil.setBlock(world, x + 13, y + 3, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 4, y + 3, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 12, y + 4, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 5, y + 4, z - 4, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 5, y + 3, z - 4, Blocks.REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x + 12, y + 3, z - 4, Blocks.REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x + 14, y + 3, z - 3, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 3, y + 3, z - 3, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 8, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE, 0, 3);
      CandyGenUtil.setBlock(world, x + 9, y + 2, z - 3, Blocks.STONE_PRESSURE_PLATE, 0, 3);
      CandyGenUtil.setBlock(world, x + 7, y + 2, z - 4, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + 10, y + 2, z - 4, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + 8, y + 3, z - 4, CandyCraft.LicoriceBrickStairs, 1, 2);
      CandyGenUtil.setBlock(world, x + 9, y + 3, z - 4, CandyCraft.LicoriceBrickStairs, 0, 2);
      CandyGenUtil.setBlock(world, x + 8, y + 4, z - 4, CandyCraft.LicoriceBrickStairs, 5, 2);
      CandyGenUtil.setBlock(world, x + 9, y + 4, z - 4, CandyCraft.LicoriceBrickStairs, 4, 2);
      CandyGenUtil.setBlock(world, x + 8, y + 2, z - 4, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + 9, y + 2, z - 4, CandyCraft.LicoriceHalfStep, 0, 3);
      this.posX += 10;
      return true;
   }

   public boolean genJumpCraft(World world, Random random, int x, int y, int z) {
      this.incrementer = -2;

      for(int i = 0; i < 8; ++i) {
         for(int k = 0; k < 2; ++k) {
            for(int j = 0; j < 41; ++j) {
               CandyGenUtil.setBlock(world, i - 1 + x, y + 1 + k * 32, z - 1 - j, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      boolean lamp = false;
      boolean side = false;

      for(int i = 0; i < 2; ++i) {
         for(int k = 0; k < 31; ++k) {
            for(int j = 0; j < 41; ++j) {
               if (!lamp) {
                  CandyGenUtil.setBlock(world, x - 1 + i * 7, y + 2 + k, z - 1 - j, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               } else {
                  CandyGenUtil.setBlock(world, x - 1 + i * 7, y + 2 + k, z - 1 - j, CandyCraft.JellyJumpRelative, 0, 3);
                  if (side) {
                     CandyGenUtil.setBlock(world, x - 1 + i * 7 + 1, y + 2 + k, z - 1 - j, CandyCraft.JawBreakerBlock, 0, 3);
                  } else {
                     CandyGenUtil.setBlock(world, x - 1 + i * 7 - 1, y + 2 + k, z - 1 - j, CandyCraft.JawBreakerBlock, 0, 3);
                  }
               }

               lamp = !lamp;
            }
         }

         side = !side;
      }

      for(int i = 0; i < 8; ++i) {
         for(int k = 0; k < 31; ++k) {
            for(int j = 0; j < 10; ++j) {
               CandyGenUtil.setBlock(world, i - 1 + x, y + 2 + k, z - 41, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 8; ++i) {
         for(int k = 0; k < 25; ++k) {
            for(int j = 0; j < 10; ++j) {
               CandyGenUtil.setBlock(world, i - 1 + x, y + 8 + k, z, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 8; ++i) {
         for(int k = 0; k < 4; ++k) {
            for(int j = 0; j < 10; ++j) {
               CandyGenUtil.setBlock(world, i - 1 + x, y + 1 + k, z, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 6; ++i) {
         for(int k = 0; k < 2; ++k) {
            for(int j = 0; j < 40; ++j) {
               CandyGenUtil.setBlock(world, i + x, y + 2 + k, z + j - 40, Blocks.WATER, 0, 3);
            }
         }
      }

      CandyGenUtil.setBlock(world, x + 1 + random.nextInt(5), y + 5, z - 2, CandyCraft.PurpleJellyJump, 0, 3);
      this.genStep(world, random, x, y, z);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      this.genStep(world, random, x, y, z + this.incrementer);
      CandyGenUtil.setAir(world, x + 3, y + 5, z - 41);
      CandyGenUtil.setAir(world, x + 2, y + 5, z - 41);
      CandyGenUtil.setAir(world, x + 3, y + 6, z - 41);
      CandyGenUtil.setAir(world, x + 2, y + 6, z - 41);
      CandyGenUtil.setAir(world, x + 3, y + 7, z - 41);
      CandyGenUtil.setAir(world, x + 2, y + 7, z - 41);
      CandyGenUtil.setBlock(world, x + 3, y + 4, z - 1, CandyCraft.CandyLadder, 2, 2);
      CandyGenUtil.setBlock(world, x + 2, y + 4, z - 1, CandyCraft.CandyLadder, 2, 2);
      CandyGenUtil.setBlock(world, x, y + 8, z - 40, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 8, z - 40, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 4, y + 8, z - 40, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 5, y + 8, z - 40, CandyCraft.JellyJumpRelative, 0, 3);

      for(int i = 0; i < 4; ++i) {
         for(int k = 0; k < 2; ++k) {
            for(int j = 0; j < 3; ++j) {
               CandyGenUtil.setBlock(world, i + 1 + x, y + 4 + k * 4, z - 42 - j, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      for(int i = 0; i < 2; ++i) {
         for(int k = 0; k < 3; ++k) {
            for(int j = 0; j < 3; ++j) {
               CandyGenUtil.setBlock(world, x + 1 + i * 3, y + 5 + k, z - 42 - j, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
            }
         }
      }

      CandyGenUtil.setBlock(world, x + 4, y + 11, z - 41, Blocks.LIT_REDSTONE_LAMP, 0, 3);
      CandyGenUtil.setBlock(world, x + 4, y + 11, z - 40, Blocks.LEVER, 11, 2);
      this.genRedstone(world, x + 4, y + 10, z - 42);
      this.genRedstone(world, x + 5, y + 8, z - 42);
      this.genRedstone(world, x + 6, y + 6, z - 42);
      this.genRedstone(world, x + 6, y + 4, z - 43);
      CandyGenUtil.setBlock(world, x + 5, y + 10, z - 42, Blocks.UNLIT_REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 8, z - 42, Blocks.REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x + 6, y + 6, z - 43, Blocks.UNLIT_REDSTONE_TORCH, 0, 3);

      for(int i = 0; i < 6; ++i) {
         CandyGenUtil.setBlock(world, x + i, y + 3, z - 41, CandyCraft.JellyJumpRelative, 2, 2);
         CandyGenUtil.setBlock(world, x + i, y + 3, z - 42, Blocks.STICKY_PISTON, 3, 2);
         this.genRedstone(world, x + i, y + 3, z - 43);
      }

      CandyGenUtil.setBlock(world, x + 2, y + 5, z - 43, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + 3, y + 5, z - 43, CandyCraft.LicoriceHalfStep, 0, 3);
      this.posX += 44;
      return true;
   }

   public void genStep(World world, Random rand, int x, int y, int z) {
      if (rand.nextBoolean()) {
         z -= 4;
         this.incrementer -= 4;
         CandyGenUtil.setBlock(world, x + 1 + rand.nextInt(5), y + 5 + rand.nextInt(4), z - 2, CandyCraft.PurpleJellyJump, 0, 3);
      } else {
         z -= 3;
         this.incrementer -= 3;
         CandyGenUtil.setBlock(world, x + 2 + rand.nextInt(3), y + 20, z - 2, CandyCraft.HoneyLamp, 0, 3);
      }

   }

   public void genWaterRoom(World world, Random random, int x, int y, int z) {
      for(int i = 0; i < 24; ++i) {
         for(int j = 1; j < 24; ++j) {
            for(int k = 0; k < 24; ++k) {
               if (i == 0 || i == 23 || j == 1 || j == 23 || k == 0 || k == 23) {
                  CandyGenUtil.setBlock(world, x + i - 12, y + j, z - k - 1, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }
            }
         }
      }

      boolean water = true;

      for(int i = 1; i < 23; ++i) {
         for(int j = 5; j < 23; ++j) {
            for(int k = 1; k < 23; ++k) {
               if (water && j < 21) {
                  CandyGenUtil.setBlock(world, x + i - 12, y + j, z - k - 1, Blocks.WATER, 0, 0);
               }

               if (random.nextInt(50) == 0) {
                  Entity slime = new EntityTornadoSlime(world);
                  slime.setLocationAndAngles((double)x + (double)i - (double)12.0F + (double)0.5F, (double)y + (double)j + (double)0.5F, (double)z - (double)k - (double)1.0F + (double)0.5F, MathHelper.wrapDegrees(random.nextFloat() * 360.0F), 0.0F);
                  world.spawnEntity(slime);
               }
            }

            water = !water;
         }
      }

      for(int i = 1; i < 23; ++i) {
         for(int k = 1; k < 23; ++k) {
            CandyGenUtil.setBlock(world, x + i - 12, y + 2, z - k - 1, (Block)(random.nextBoolean() ? CandyCraft.LicoriceHalfStep : (random.nextBoolean() ? CandyCraft.JawBreakerBlock : (random.nextBoolean() ? CandyCraft.LicoriceBlock : CandyCraft.JawBreakerLight))), 0, 0);
         }
      }

      CandyGenUtil.setAir(world, x, y + 2, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 2, z - 1);
      CandyGenUtil.setAir(world, x, y + 3, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 3, z - 1);
      CandyGenUtil.setAir(world, x, y + 4, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 4, z - 1);
      CandyGenUtil.setAir(world, x, y + 20, z - 24);
      CandyGenUtil.setAir(world, x + 1, y + 20, z - 24);
      CandyGenUtil.setAir(world, x, y + 21, z - 24);
      CandyGenUtil.setAir(world, x + 1, y + 21, z - 24);
      CandyGenUtil.setAir(world, x, y + 22, z - 24);
      CandyGenUtil.setAir(world, x + 1, y + 22, z - 24);

      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 19; ++j) {
            CandyGenUtil.setBlock(world, x + i - 1, y + j + 5, z - 27, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
         }
      }

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 23; ++j) {
            CandyGenUtil.setBlock(world, x - 1, y + j + 1, z - 25 - i, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
         }
      }

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 23; ++j) {
            CandyGenUtil.setBlock(world, x + 2, y + j + 1, z - 25 - i, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
         }
      }

      CandyGenUtil.setBlock(world, x + 0, y + 23, z - 25, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 0, y + 23, z - 26, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 23, z - 25, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 23, z - 26, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 0, y + 1, z - 25, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 0, y + 1, z - 26, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 1, z - 25, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 1, z - 26, CandyCraft.JellyJumpRelative, 0, 3);
      CandyGenUtil.setBlock(world, x + 0, y, z - 25, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 0, y, z - 26, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y, z - 25, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y, z - 26, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y + 1, z - 27, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 1, z - 27, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
      this.posX += 27;
   }

   public void genMob(World world, Random random, int x, int y, int z) {
      for(int i = 0; i < 22; ++i) {
         for(int j = 0; j < 7; ++j) {
            for(int k = 0; k < 55; ++k) {
               if (i == 0 || i == 21 || j == 0 || j == 6 || k == 0 || k == 54) {
                  CandyGenUtil.setBlock(world, x + i - 10, y + j + 1, z - k - 1, random.nextInt(3) != 0 ? CandyCraft.JawBreakerBlock : (random.nextInt(10) == 0 ? CandyCraft.LicoriceBlock : CandyCraft.LicoriceBrick), 0, 3);
               }
            }
         }
      }

      for(int k = 6; k < 56; ++k) {
         this.genRedstone(world, x + 8, y - 1, z - k);
         this.genRedstone(world, x - 7, y - 1, z - k);
      }

      for(int k = 6; k < 20; ++k) {
         if (k != 13 && k != 12) {
            this.genRedstone(world, x - 12 + k, y - 1, z - 55);
         }

         this.genRedstone(world, x - 12 + k, y - 1, z - 53);
         this.genRedstone(world, x - 12 + k, y - 1, z - 51);
      }

      CandyGenUtil.setBlock(world, x + 2, y, z - 55, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x - 1, y, z - 55, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + 1, y, z - 55, Blocks.UNLIT_REDSTONE_TORCH, 0, 3);
      CandyGenUtil.setBlock(world, x, y, z - 55, Blocks.UNLIT_REDSTONE_TORCH, 0, 3);
      this.genColumn(world, random, x + 8, y + 1, z - 7, true, 1);
      this.genColumn(world, random, x + 8, y + 1, z - 13, true, 2);
      this.genColumn(world, random, x + 8, y + 1, z - 19, true, 3);
      this.genColumn(world, random, x + 8, y + 1, z - 25, true, 4);
      this.genColumn(world, random, x + 8, y + 1, z - 31, true, 5);
      this.genColumn(world, random, x + 8, y + 1, z - 37, true, 6);
      this.genColumn(world, random, x + 8, y + 1, z - 43, true, 7);
      this.genColumn(world, random, x + 8, y + 1, z - 49, true, 8);
      this.genColumn(world, random, x - 7, y + 1, z - 7, false, 1);
      this.genColumn(world, random, x - 7, y + 1, z - 13, false, 2);
      this.genColumn(world, random, x - 7, y + 1, z - 19, false, 3);
      this.genColumn(world, random, x - 7, y + 1, z - 25, false, 4);
      this.genColumn(world, random, x - 7, y + 1, z - 31, false, 5);
      this.genColumn(world, random, x - 7, y + 1, z - 37, false, 6);
      this.genColumn(world, random, x - 7, y + 1, z - 43, false, 7);
      this.genColumn(world, random, x - 7, y + 1, z - 49, false, 8);
      CandyGenUtil.setAir(world, x, y + 2, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 2, z - 1);
      CandyGenUtil.setAir(world, x, y + 3, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 3, z - 1);
      CandyGenUtil.setAir(world, x, y + 4, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 4, z - 1);
      CandyGenUtil.setBlock(world, x, y + 2, z - 55, Blocks.IRON_DOOR, 3, 2);
      CandyGenUtil.setBlock(world, x, y + 3, z - 55, Blocks.IRON_DOOR, 8, 2);
      CandyGenUtil.setBlock(world, x + 1, y + 2, z - 55, Blocks.IRON_DOOR, 3, 2);
      CandyGenUtil.setBlock(world, x + 1, y + 3, z - 55, Blocks.IRON_DOOR, 9, 2);
      CandyGenUtil.setBlock(world, x + 3, y, z - 53, Blocks.POWERED_REPEATER, 1, 2);
      CandyGenUtil.setBlock(world, x - 2, y, z - 51, Blocks.POWERED_REPEATER, 1, 2);
      this.posX += 55;
   }

   public void genColumn(World world, Random random, int x, int y, int z, boolean side, int id) {
      CandyGenUtil.setBlock(world, x, y, z, Blocks.MOB_SPAWNER, 0, 2);
      TileEntityMobSpawner spawner = (TileEntityMobSpawner)CandyGenUtil.getTile(world, x, y, z);
      int mob = random.nextInt(3);
      CandyGenUtil.setSpawner(spawner, mob == 0 ? "SprinterSlime" : (mob == 1 ? "KamikazeSlime" : "TornadoSlime"));
      CandyGenUtil.setBlock(world, x, y + 1, z, CandyCraft.LicoriceBlock, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 5, z, CandyCraft.LicoriceBlock, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 2, z, Blocks.REDSTONE_LAMP, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 3, z, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 4, z, CandyCraft.JawBreakerBlock, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 5, z, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 5, z, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 1, z, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 1, z, CandyCraft.LicoriceBrick, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? -1 : 1), y + 1, z + 1, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? -1 : 1), y + 1, z - 1, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? -1 : 1), y + 5, z + 1, CandyCraft.LicoriceHalfStep, 8, 2);
      CandyGenUtil.setBlock(world, x + (side ? -1 : 1), y + 5, z - 1, CandyCraft.LicoriceHalfStep, 8, 2);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 4, z, CandyCraft.LicoriceHalfStep, 8, 2);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 4, z, CandyCraft.LicoriceHalfStep, 8, 2);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 2, z, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 2, z, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 1, z + 1, CandyCraft.LicoriceBrickStairs, 3, 2);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 1, z + 1, CandyCraft.LicoriceBrickStairs, 3, 2);
      CandyGenUtil.setBlock(world, x, y + 1, z + 1, CandyCraft.LicoriceBrickStairs, 3, 2);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 5, z + 1, CandyCraft.LicoriceBrickStairs, 7, 2);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 5, z + 1, CandyCraft.LicoriceBrickStairs, 7, 2);
      CandyGenUtil.setBlock(world, x, y + 5, z + 1, CandyCraft.LicoriceBrickStairs, 7, 2);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 1, z - 1, CandyCraft.LicoriceBrickStairs, 2, 2);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 1, z - 1, CandyCraft.LicoriceBrickStairs, 2, 2);
      CandyGenUtil.setBlock(world, x, y + 1, z - 1, CandyCraft.LicoriceBrickStairs, 2, 2);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 5, z - 1, CandyCraft.LicoriceBrickStairs, 6, 2);
      CandyGenUtil.setBlock(world, x + (side ? 2 : -2), y + 5, z - 1, CandyCraft.LicoriceBrickStairs, 6, 2);
      CandyGenUtil.setBlock(world, x, y + 5, z - 1, CandyCraft.LicoriceBrickStairs, 6, 2);
      CandyGenUtil.setBlock(world, x, y + 2, z - 1, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 2, z + 1, CandyCraft.LicoriceHalfStep, 0, 3);
      CandyGenUtil.setBlock(world, x + (side ? -1 : 1), y + 1, z, Blocks.LEVER, side ? 2 : 1, 2);
      CandyGenUtil.setBlock(world, x + (side ? 1 : -1), y + 1, z, Blocks.REDSTONE_TORCH, side ? 1 : 2, 2);
      this.genRedstone(world, x + (side ? 1 : -1), y - 1, z);
      this.genRedstone(world, x + (side ? 1 : -1), y - 2, z - 1);
      CandyGenUtil.setAir(world, x + (side ? 1 : -1), y, z - 1);
      if (id != 8) {
         CandyGenUtil.setBlock(world, x, y - 1, z - 2, Blocks.POWERED_REPEATER, 0, 2);
      }

   }

   public void genMiniBossRoom(World world, Random random, int x, int y, int z) {
      y -= 2;

      for(int i = 0; i < 24; ++i) {
         for(int j = 0; j < 24; ++j) {
            for(int k = 0; k < 24; ++k) {
               if (i == 0 || i == 23 || j == 0 || j == 1 || j == 23 || k == 0 || k == 23) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }

               if (k != 0 && k != 23 && i != 23 && i != 0 && j == 1 && (i == 1 || i == 3 || i == 5 || i == 7 || i == 22 || i == 20 || i == 18 || i == 16 || k == 1 || k == 3 || k == 5 || k == 7 || k == 22 || k == 20 || k == 18 || k == 16)) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, CandyCraft.GrenadineFlow, 0, 3);
               }

               if (j == 6 && (i == 1 || i == 22 || j == 4 || k == 1 || k == 22)) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }

               if (j == 6 && (i == 1 && k == 1 || i == 22 && k == 1 || i == 1 && k == 22 || i == 22 && k == 22)) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, CandyCraft.GrenadineFlow, 0, 3);
               }
            }
         }
      }

      CandyGenUtil.setBlock(world, x - 10, y + 23, z - 2, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x + 11, y + 23, z - 2, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x - 10, y + 23, z - 23, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x + 11, y + 23, z - 23, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 2, z - 24, CandyCraft.JellySentryKeyHole, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 3, z - 24, CandyCraft.JellySentryKeyHole, 0, 3);
      CandyGenUtil.setAir(world, x + 1, y + 4, z - 1);
      CandyGenUtil.setAir(world, x, y + 4, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 5, z - 1);
      CandyGenUtil.setAir(world, x, y + 5, z - 1);
      EntityPEZSlime slime = new EntityPEZSlime(world);
      slime.setPosition((double)(x + 1), (double)(y + 2), (double)(z - 12));
      world.spawnEntity(slime);
      this.posX += 24;
   }

   public void genBossRoom(World world, Random random, int x, int y, int z) {
      for(int i = 0; i < 50; ++i) {
         for(int j = 0; j < 50; ++j) {
            for(int k = 0; k < 49; ++k) {
               if (i == 0 || i == 49 || j == 0 || j == 1 || j == 49 || k == 0 || k == 48) {
                  CandyGenUtil.setBlock(world, x + i - 24, y + j - 1, z - k - 1, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }
            }
         }
      }

      for(int i = 0; i < 50; i += 2) {
         for(int k = 0; k < 49; ++k) {
            if (i != 0 && i != 49 && k != 0 && k != 48) {
               CandyGenUtil.setBlock(world, x + i - 24, y, z - k - 1, CandyCraft.BarleyHalfStep, 0, 3);
            }
         }
      }

      for(int i = 0; i < 50; ++i) {
         for(int k = 0; k < 49; k += 2) {
            if (i != 0 && i != 49 && k != 0 && k != 48) {
               CandyGenUtil.setBlock(world, x + i - 24, y, z - k - 1, CandyCraft.BarleyHalfStep, 0, 3);
            }
         }
      }

      CandyGenUtil.setBlock(world, x - 23, y + 48, z - 2, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x + 24, y + 48, z - 2, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x - 23, y + 48, z - 48, Blocks.GLASS, 0, 3);
      CandyGenUtil.setBlock(world, x + 24, y + 48, z - 48, Blocks.GLASS, 0, 3);
      CandyGenUtil.setAir(world, x + 1, y + 3, z - 1);
      CandyGenUtil.setAir(world, x, y + 3, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 4, z - 1);
      CandyGenUtil.setAir(world, x, y + 4, z - 1);
      CandyGenUtil.setBlock(world, x, y + 2, z - 49, CandyCraft.JellyBossKeyHole, 0, 3);
      CandyGenUtil.setBlock(world, x, y + 1, z - 49, CandyCraft.JellyBossKeyHole, 0, 3);
      EntityKingSlime slime = new EntityKingSlime(world);
      slime.Start = true;
      slime.SX = (double)(x + 1);
      slime.SY = (double)(y + 2);
      slime.SZ = (double)(z - 25);
      slime.setPosition((double)(x + 1), (double)(y + 2), (double)(z - 25));
      world.spawnEntity(slime);
      this.posX += 49;
   }

   private void genReward(World world, Random random, int x, int y, int z) {
      for(int i = 0; i < 24; ++i) {
         for(int j = 0; j < 24; ++j) {
            for(int k = 0; k < 24; ++k) {
               if (i == 0 || i == 23 || j == 0 || j == 1 || j == 23 || k == 0 || k == 23) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }

               if (k != 0 && k != 23 && i != 23 && i != 0 && j == 1 && (i == 1 || i == 3 || i == 5 || i == 7 || i == 22 || i == 20 || i == 18 || i == 16 || k == 1 || k == 3 || k == 5 || k == 7 || k == 22 || k == 20 || k == 18 || k == 16)) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, CandyCraft.GrenadineFlow, 0, 3);
               }

               if ((j == 12 || j == 6) && (i == 1 || i == 22 || j == 4 || k == 1 || k == 22)) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, random.nextInt(10) != 0 ? CandyCraft.JawBreakerBlock : CandyCraft.JawBreakerLight, 0, 3);
               }

               if ((j == 12 || j == 6) && (i == 1 && k == 1 || i == 22 && k == 1 || i == 1 && k == 22 || i == 22 && k == 22)) {
                  CandyGenUtil.setBlock(world, x + i - 11, y + j, z - k - 1, CandyCraft.GrenadineFlow, 0, 3);
               }
            }
         }
      }

      CandyGenUtil.setAir(world, x + 1, y + 2, z - 1);
      CandyGenUtil.setAir(world, x, y + 2, z - 1);
      CandyGenUtil.setAir(world, x + 1, y + 3, z - 1);
      CandyGenUtil.setAir(world, x, y + 3, z - 1);
      CandyGenUtil.setBlock(world, x, y + 2, z - 15, CandyCraft.CandyChest, 0, 3);
      TileEntityChest chest = (TileEntityChest)CandyGenUtil.getTile(world, x, y + 2, z - 15);
      ItemStack fork = new ItemStack(CandyCraft.Fork, 1);
      fork.addEnchantment(CandyCraft.eat, 1);
      fork.addEnchantment(Enchantments.SHARPNESS, 5);
      ItemStack bow = new ItemStack(CandyCraft.CaramelBow, 1);
      bow.addEnchantment(Enchantments.POWER, 4);
      bow.addEnchantment(CandyCraft.honeyGlue, 2);

      for(int i = 0; i < chest.getSizeInventory(); ++i) {
         ItemStack[] rewards = new ItemStack[]{new ItemStack(CandyCraft.SugarCrystal, random.nextInt(8) + 4), new ItemStack(CandyCraft.Licorice, random.nextInt(14) + 8), new ItemStack(CandyCraft.BarleySugar, random.nextInt(8) + 3), new ItemStack(CandyCraft.ChocolateCoins, random.nextInt(40) + 16), new ItemStack(CandyCraft.JumpWand, 1), new ItemStack(CandyCraft.JellyWand, 1), new ItemStack(CandyCraft.SugarBlock, random.nextInt(7) + 3), new ItemStack(CandyCraft.PEZ, random.nextInt(12) + 4), new ItemStack(CandyCraft.Gummy, random.nextInt(12) + 6), fork, bow, new ItemStack(CandyCraft.SugarFactory, random.nextInt(2) + 1), new ItemStack(CandyCraft.LollipopSeeds, random.nextInt(12) + 6), new ItemStack(CandyCraft.CottonCandy, random.nextInt(3) + 6), new ItemStack(CandyCraft.CranberryFishCooked, random.nextInt(3) + 6), new ItemStack(CandyCraft.CranberryScale, random.nextInt(3) + 6), new ItemStack(CandyCraft.MysteriousEgg, 1)};
         if (random.nextInt(3) >= 1) {
            chest.setInventorySlotContents(i, rewards[random.nextInt(rewards.length)]);
         }
      }

      chest.setInventorySlotContents(0, new ItemStack(CandyCraft.JellyCrown));
      CandyGenUtil.setBlock(world, x + 1, y + 2, z - 15, CandyCraft.BlockTeleporter, 0, 3);
      TileEntityTeleporter port = (TileEntityTeleporter)CandyGenUtil.getTile(world, x + 1, y + 2, z - 15);
      port.x = this.xb;
      port.y = this.yb;
      port.z = this.zb;
      port.generated = true;
      port.dim = this.dim;
   }

   @Override
   public boolean generate(World world, Random random, BlockPos pos) {
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();
      world.getChunk(new BlockPos(i, 0, k));
      this.spawnRoom(world, random, i - 1, j - 1, k - 1);
      int r = random.nextInt(4);
      if (r == 0) {
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genJumpCraft(world, random, i + 5, j - 3, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genWaterRoom(world, random, i + 7, j, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genMob(world, random, i + 7, j, k - this.posX);
      }

      if (r == 1) {
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genMob(world, random, i + 7, j, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genWaterRoom(world, random, i + 7, j, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genJumpCraft(world, random, i + 5, j - 3, k - this.posX);
      }

      if (r == 2) {
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genJumpCraft(world, random, i + 5, j - 3, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genMob(world, random, i + 7, j, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genWaterRoom(world, random, i + 7, j, k - this.posX);
      }

      if (r == 3) {
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genWaterRoom(world, random, i + 7, j, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genMob(world, random, i + 7, j, k - this.posX);
         this.genCoridor(world, random, i + 7, j, k - this.posX);
         this.genJumpCraft(world, random, i + 5, j - 3, k - this.posX);
      }

      this.genCoridor(world, random, i + 7, j, k - this.posX);
      world.getChunk(new BlockPos(i, 0, k - 150));
      this.genMiniBossRoom(world, random, i + 7, j, k - this.posX);
      this.genCoridor(world, random, i + 7, j - 2, k - this.posX);
      boolean b = random.nextBoolean();
      if (b) {
         this.genJumpCraft(world, random, i + 5, j - 5, k - this.posX);
         this.genCoridor(world, random, i + 7, j - 2, k - this.posX);
         this.genMob(world, random, i + 7, j - 2, k - this.posX);
      } else {
         this.genMob(world, random, i + 7, j - 2, k - this.posX);
         this.genCoridor(world, random, i + 7, j - 2, k - this.posX);
         this.genJumpCraft(world, random, i + 5, j - 5, k - this.posX);
      }

      this.genCoridor(world, random, i + 7, j - 2, k - this.posX);
      this.genBossRoom(world, random, i + 7, j - 2, k - this.posX);
      this.genCoridor(world, random, i + 7, j - 3, k - this.posX);
      this.genReward(world, random, i + 7, j - 3, k - this.posX);
      return true;
   }
}
