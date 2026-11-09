package com.thecramp.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class DemonEye extends Item {
   public DemonEye() {
      this.maxStackSize = 1;
      this.setMaxDamage(1500);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      player.jump();
      player.fallDistance = 0.0F;
      if (!world.isRemote) {
         stack.damageItem(1, player);
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
   }
}
