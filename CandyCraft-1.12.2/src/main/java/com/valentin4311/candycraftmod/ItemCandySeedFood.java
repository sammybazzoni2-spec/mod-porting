package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCandySeedFood extends ItemSeedFood {
   private Block cropId;

   public ItemCandySeedFood(int heal, float sat, Block crop) {
      super(heal, sat, crop, CandyCraft.CandySoil);
      this.cropId = crop;
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack stack = player.getHeldItem(hand);
      if (facing != EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else if (player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(pos.up(), facing, stack)) {
         Block soil = world.getBlockState(pos).getBlock();
         if (soil == CandyCraft.CandySoil && world.isAirBlock(pos.up())) {
            world.setBlockState(pos.up(), this.cropId.getDefaultState(), 11);
            stack.shrink(1);
            return EnumActionResult.SUCCESS;
         } else {
            return EnumActionResult.FAIL;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }
}
