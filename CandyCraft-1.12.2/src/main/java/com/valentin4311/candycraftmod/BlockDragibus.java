package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDragibus extends BlockCrops {
   public BlockDragibus() {
   }

   @Override
   protected boolean canSustainBush(IBlockState state) {
      return state.getBlock() == CandyCraft.CandySoil;
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return (world.getLight(pos) >= 8 || world.canSeeSky(pos)) && this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return CandyCraft.Dragibus;
   }

   @Override
   public int quantityDropped(Random random) {
      return random.nextInt(3) + 1;
   }

   @Override
   protected Item getSeed() {
      return CandyCraft.Dragibus;
   }

   @Override
   protected Item getCrop() {
      return CandyCraft.Dragibus;
   }

   @Override
   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      Random rand = world instanceof World ? ((World)world).rand : new Random();
      int count = this.quantityDropped(rand);
      if (!this.isMaxAge(state)) {
         count = 1;
      }

      for (int i = 0; i < count; ++i) {
         drops.add(new ItemStack(CandyCraft.Dragibus, 1, 0));
      }
   }
}
