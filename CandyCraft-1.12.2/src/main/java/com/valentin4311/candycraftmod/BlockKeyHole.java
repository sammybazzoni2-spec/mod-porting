package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockKeyHole extends Block {
   private int items;

   public BlockKeyHole(Material material, int id) {
      super(material);
      this.items = id;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Item i = null;
      if (this.items == 0) {
         i = CandyCraft.JellyKey;
      }

      if (this.items == 1) {
         i = CandyCraft.Jelly2Key;
      }

      ItemStack held = player.getHeldItem(hand);
      if (!world.isRemote && !held.isEmpty() && held.getItem() == i) {
         world.setBlockToAir(pos);
         if (world.getBlockState(pos.up()).getBlock() == this) {
            world.setBlockToAir(pos.up());
         }

         if (world.getBlockState(pos.down()).getBlock() == this) {
            world.setBlockToAir(pos.down());
         }

         held.shrink(1);
         return true;
      } else {
         return true;
      }
   }
}
