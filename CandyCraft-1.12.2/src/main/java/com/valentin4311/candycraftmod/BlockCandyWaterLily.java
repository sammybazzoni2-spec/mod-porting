package com.valentin4311.candycraftmod;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCandyWaterLily extends BlockBush {
   private static final AxisAlignedBB LILY_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.015625D, 1.0D);

   public BlockCandyWaterLily() {
      this.setTickRandomly(true);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return LILY_AABB;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
      return LILY_AABB;
   }

   @Override
   public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entity, boolean isActualState) {
      if (entity == null || !(entity instanceof EntityBoat)) {
         super.addCollisionBoxToList(state, world, pos, entityBox, collidingBoxes, entity, isActualState);
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(CandyCraft.CandyWaterLily);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (this == CandyCraft.MarshmallowFlowerBlock && !world.isRemote) {
         world.setBlockState(pos, CandyCraft.CandyWaterLily.getDefaultState());
         spawnAsEntity(world, pos, new ItemStack(CandyCraft.MarshmallowFlower));
      }

      return this != CandyCraft.CandyWaterLily;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote && this != CandyCraft.MarshmallowFlowerBlock && world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(80) == 0) {
         world.setBlockState(pos, CandyCraft.MarshmallowFlowerBlock.getDefaultState());
      }
   }

   @Override
   protected boolean canSustainBush(IBlockState state) {
      return state.getMaterial() == Material.WATER && state.getValue(BlockLiquid.LEVEL) == 0;
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && this.canBlockStay(world, pos, this.getDefaultState());
   }
}
