package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemCandyBed extends Item {
   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (world.isRemote) {
         return EnumActionResult.SUCCESS;
      } else if (facing != EnumFacing.UP) {
         return EnumActionResult.FAIL;
      } else {
         ItemStack stack = player.getHeldItem(hand);
         IBlockState state = world.getBlockState(pos);
         Block block = state.getBlock();
         boolean replaceable = block.isReplaceable(world, pos);
         if (!replaceable) {
            pos = pos.up();
         }
         int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
         EnumFacing dir = EnumFacing.byHorizontalIndex(i);
         BlockPos head = pos.offset(dir);
         if (player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(head, facing, stack)) {
            boolean ok1 = replaceable || world.isAirBlock(pos);
            boolean ok2 = world.getBlockState(head).getBlock().isReplaceable(world, head) || world.isAirBlock(head);
            if (ok1 && ok2 && world.getBlockState(pos.down()).isTopSolid() && world.getBlockState(head.down()).isTopSolid()) {
               IBlockState foot = CandyCraft.CottonCandyBedBlock.getDefaultState().withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false)).withProperty(BlockBed.FACING, dir).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);
               world.setBlockState(pos, foot, 10);
               world.setBlockState(head, foot.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 10);
               if (!player.capabilities.isCreativeMode) {
                  stack.shrink(1);
               }
               return EnumActionResult.SUCCESS;
            }
         }
         return EnumActionResult.FAIL;
      }
   }
}
