package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemCandyFlowerPad extends ItemBlock {
   public ItemCandyFlowerPad(Block block) {
      super(block);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      RayTraceResult ray = this.rayTrace(world, player, true);
      if (ray == null) {
         return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
      }
      if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
         BlockPos pos = ray.getBlockPos();
         if (!world.isBlockModifiable(player, pos) || !player.canPlayerEdit(pos.offset(ray.sideHit), ray.sideHit, stack)) {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
         }
         IBlockState state = world.getBlockState(pos);
         BlockPos up = pos.up();
         if (state.getMaterial() == Material.WATER && state.getBlock().getMetaFromState(state) == 0 && world.isAirBlock(up)) {
            world.setBlockState(up, CandyCraft.MarshmallowFlowerBlock.getDefaultState(), 11);
            if (!player.capabilities.isCreativeMode) {
               stack.shrink(1);
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
         }
      }
      return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
   }
}
