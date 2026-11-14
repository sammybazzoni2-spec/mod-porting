package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChewingGumPlate extends Block {
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.015625D, 1.0D);

   protected BlockChewingGumPlate(Material material) {
      super(material);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BOX;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
      return NULL_AABB;
   }

   @Override
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   @Override
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   @Override
   public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }

   @Override
   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).isOpaqueCube();
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).isOpaqueCube();
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      if (!this.canBlockStay(world, pos)) {
         world.setBlockToAir(pos);
      }
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!(entity instanceof EntityBeetle) && !(entity instanceof EntityKingBeetle) && !(entity instanceof EntityBossBeetle) && entity.posY - (double)pos.getY() < 1.63D) {
         if (entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(CandyCraft.ChewingGumEmblem))) {
            return;
         }

         entity.motionX *= 0.2D;
         entity.motionZ *= 0.2D;
         entity.motionY = 0.0D;
      }
   }
}
