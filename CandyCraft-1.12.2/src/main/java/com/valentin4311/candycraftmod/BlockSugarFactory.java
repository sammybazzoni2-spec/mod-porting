package com.valentin4311.candycraftmod;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSugarFactory extends BlockContainer {
   private boolean advancedMode;

   protected BlockSugarFactory(Material material, boolean advanced) {
      super(material);
      this.advancedMode = advanced;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity != null && !player.isSneaking()) {
         player.openGui(CandyCraft.modInstance, 1, world, pos.getX(), pos.getY(), pos.getZ());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      TileEntity te = world.getTileEntity(pos);
      if (te instanceof TileEntitySugarFactory) {
         InventoryHelper.dropInventoryItems(world, pos, (IInventory)te);
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public boolean hasComparatorInputOverride(IBlockState state) {
      return true;
   }

   @Override
   public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
      return Container.calcRedstone(world.getTileEntity(pos));
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntitySugarFactory();
   }
}
