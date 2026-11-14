package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLollipopPlant extends BlockBush {
   public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);

   protected BlockLollipopPlant() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      this.setCreativeTab(null);
      this.setHardness(0.0F);
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, AGE);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(AGE, meta & 7);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(AGE);
   }

   @Override
   protected boolean canSustainBush(IBlockState state) {
      return state.getBlock() == CandyCraft.CandySoil;
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      int age = state.getValue(AGE);
      double maxY = 0.3D;
      if (age < 7 && age > 3) {
         maxY = 0.6D;
      } else if (age >= 7) {
         maxY = 1.0D;
      }

      return new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, maxY, 0.625D);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      super.updateTick(world, pos, state, rand);
      if (world.getLightFromNeighbors(pos.up()) >= 9) {
         int l = state.getValue(AGE);
         if (l < 7) {
            float f = this.getGrowthRate(world, pos);
            if (rand.nextInt((int)(25.0F / f) + 1) == 0) {
               ++l;
               world.setBlockState(pos, state.withProperty(AGE, l), 2);
            }
         }

         if (l == 7 && world.isAirBlock(pos.up())) {
            float f = this.getGrowthRate(world, pos);
            if (rand.nextInt((int)(25.0F / f) + 1) == 0 && world.isAirBlock(pos.up())) {
               world.setBlockState(pos.up(), CandyCraft.LollipopBlock.getDefaultState(), 2);
            }
         }
      }
   }

   public void fertilize(World world, BlockPos pos) {
      IBlockState state = world.getBlockState(pos);
      int l = state.getValue(AGE) + net.minecraft.util.math.MathHelper.getInt(world.rand, 2, 5);
      if (l > 7) {
         l = 7;
      }

      world.setBlockState(pos, state.withProperty(AGE, l), 2);
   }

   private float getGrowthRate(World world, BlockPos pos) {
      float f = 1.0F;
      Block l = world.getBlockState(pos.north()).getBlock();
      Block i1 = world.getBlockState(pos.south()).getBlock();
      Block j1 = world.getBlockState(pos.west()).getBlock();
      Block k1 = world.getBlockState(pos.east()).getBlock();
      Block l1 = world.getBlockState(pos.add(-1, 0, -1)).getBlock();
      Block i2 = world.getBlockState(pos.add(1, 0, -1)).getBlock();
      Block j2 = world.getBlockState(pos.add(1, 0, 1)).getBlock();
      Block k2 = world.getBlockState(pos.add(-1, 0, 1)).getBlock();
      boolean flag = j1 == this || k1 == this;
      boolean flag1 = l == this || i1 == this;
      boolean flag2 = l1 == this || i2 == this || j2 == this || k2 == this;
      for (int l2 = pos.getX() - 1; l2 <= pos.getX() + 1; ++l2) {
         for (int i3 = pos.getZ() - 1; i3 <= pos.getZ() + 1; ++i3) {
            BlockPos soilPos = new BlockPos(l2, pos.getY() - 1, i3);
            Block j3 = world.getBlockState(soilPos).getBlock();
            float f1 = 0.0F;
            if (j3 == CandyCraft.CandySoil) {
               f1 = 1.0F;
               if (j3.isFertile(world, soilPos)) {
                  f1 = 3.0F;
               }
            }

            if (l2 != pos.getX() || i3 != pos.getZ()) {
               f1 /= 4.0F;
            }

            f += f1;
         }
      }

      if (flag2 || flag && flag1) {
         f /= 2.0F;
      }

      return f;
   }

   protected Item getSeedItem() {
      return CandyCraft.LollipopSeeds;
   }

   @Override
   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      super.getDrops(drops, world, pos, state, fortune);
      Random rand = world instanceof World ? ((World)world).rand : new Random();
      int age = state.getValue(AGE);
      if (age >= 7) {
         for (int n = 0; n < 3 + fortune; ++n) {
            if (rand.nextInt(15) <= age) {
               drops.add(new ItemStack(this.getSeedItem(), 1, 0));
            }
         }
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return CandyCraft.LollipopSeeds;
   }

   @Override
   public int quantityDropped(Random random) {
      return 1;
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(CandyCraft.LollipopSeeds);
   }
}
