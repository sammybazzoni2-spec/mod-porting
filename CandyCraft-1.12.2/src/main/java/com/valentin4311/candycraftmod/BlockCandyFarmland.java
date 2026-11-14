package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockCandyFarmland extends Block {
   public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

   protected BlockCandyFarmland() {
      super(Material.GROUND);
      this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, 0));
      this.setTickRandomly(true);
      this.setLightOpacity(0);
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, MOISTURE);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(MOISTURE, meta & 7);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(MOISTURE);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BOX;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   @Override
   public boolean isFertile(World world, BlockPos pos) {
      IBlockState state = world.getBlockState(pos);
      return state.getBlock() == CandyCraft.CandySoil && state.getValue(MOISTURE) > 0;
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
      return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
   }

   @Override
   public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
      return plantable == CandyCraft.DragiCrops || plantable == CandyCraft.LollipopPlant;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!this.isWaterNearby(world, pos) && !world.isRainingAt(pos.up())) {
         int l = state.getValue(MOISTURE);
         if (l > 0) {
            world.setBlockState(pos, state.withProperty(MOISTURE, l - 1), 2);
         } else if (!this.isCropsNearby(world, pos)) {
            world.setBlockState(pos, CandyCraft.FlourBlock.getDefaultState());
         }
      } else {
         world.setBlockState(pos, state.withProperty(MOISTURE, 7), 2);
      }
   }

   @Override
   public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
      if (!world.isRemote && world.rand.nextFloat() < fallDistance - 0.5F) {
         if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing")) {
            return;
         }

         world.setBlockState(pos, CandyCraft.FlourBlock.getDefaultState());
      }

      super.onFallenUpon(world, pos, entity, fallDistance);
   }

   private boolean isCropsNearby(World world, BlockPos pos) {
      Block plant = world.getBlockState(pos.up()).getBlock();
      return plant instanceof IPlantable && this.canSustainPlant(world.getBlockState(pos), world, pos, EnumFacing.UP, (IPlantable)plant);
   }

   private boolean isWaterNearby(World world, BlockPos pos) {
      for (BlockPos.MutableBlockPos p : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
         if (world.getBlockState(p).getMaterial() == Material.WATER) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      super.neighborChanged(state, world, pos, block, fromPos);
      Material material = world.getBlockState(pos.up()).getMaterial();
      if (material.isSolid()) {
         world.setBlockState(pos, CandyCraft.FlourBlock.getDefaultState());
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(CandyCraft.FlourBlock);
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(CandyCraft.FlourBlock);
   }
}
