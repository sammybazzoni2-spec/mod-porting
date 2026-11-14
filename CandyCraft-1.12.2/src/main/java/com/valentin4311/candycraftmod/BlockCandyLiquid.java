package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * Grenadine liquid. The custom flow code of the 1.7.10 version is replaced by the Forge fluid
 * implementation (BlockFluidClassic); only the interaction with water is kept.
 */
public abstract class BlockCandyLiquid extends BlockFluidClassic {
   protected BlockCandyLiquid(Material material) {
      super(CandyCraft.fluid, material);
      this.setTickRandomly(true);
   }

   public boolean isGrenadine() {
      return this == CandyCraft.GrenadineFlow || this == CandyCraft.GrenadineStatic;
   }

   public boolean isWater(Block block) {
      return block == Blocks.WATER || block == Blocks.FLOWING_WATER;
   }

   public static float getLiquidHeightPercent(int meta) {
      if (meta >= 8) {
         meta = 0;
      }

      return (float)(meta + 1) / 9.0F;
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      super.neighborChanged(state, world, pos, block, fromPos);
      this.checkWater(world, pos);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      this.checkWater(world, pos);
   }

   private void checkWater(World world, BlockPos pos) {
      if (world.getBlockState(pos).getBlock() != this || world.isRemote) {
         return;
      }

      boolean flag = false;
      for (net.minecraft.util.EnumFacing facing : net.minecraft.util.EnumFacing.values()) {
         if (facing != net.minecraft.util.EnumFacing.DOWN && this.isWater(world.getBlockState(pos.offset(facing)).getBlock())) {
            flag = true;
         }
      }

      if (flag) {
         if (this.isSourceBlock(world, pos)) {
            world.setBlockState(pos, CandyCraft.GrenadineBlock.getDefaultState());
         } else {
            world.setBlockState(pos, CandyCraft.GrenadineBlock.getStateFromMeta(1), 3);
         }

         this.fizz(world, pos);
      } else if (this.isWater(world.getBlockState(pos.down()).getBlock())) {
         world.setBlockState(pos.down(), CandyCraft.GrenadineBlock.getDefaultState());
         this.fizz(world, pos.down());
      }
   }

   protected void fizz(World world, BlockPos pos) {
      world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; ++l) {
         world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
      }
   }
}
