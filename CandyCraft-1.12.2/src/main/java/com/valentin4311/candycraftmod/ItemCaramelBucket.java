package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCaramelBucket extends Item {
   public ItemCaramelBucket() {
      this.setMaxStackSize(1);
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
         }
         if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 1));
         }
         return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
      }
      return stack;
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 32;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.DRINK;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      player.setActiveHand(hand);
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
   }
}
