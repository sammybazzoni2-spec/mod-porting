package com.valentin4311.candycraftmod;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCandyWorkbench extends BlockWorkbench {
   protected BlockCandyWorkbench() {
      this.setCreativeTab(CreativeTabs.DECORATIONS);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (world.isRemote) {
         return true;
      } else {
         player.openGui(CandyCraft.modInstance, 4301, world, pos.getX(), pos.getY(), pos.getZ());
         return true;
      }
   }
}
