package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemLollipop extends ItemFood {
   public ItemLollipop() {
      super(1, false);
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
      if (target.getHealth() < target.getMaxHealth()) {
         if (!target.world.isRemote) {
            target.heal(1.0F);
            if (!player.capabilities.isCreativeMode) {
               stack.shrink(1);
            }
            if (target.world instanceof WorldServer) {
               ((WorldServer)target.world).spawnParticle(EnumParticleTypes.HEART, target.posX, target.posY + 0.5D + (double)target.height * 0.5D, target.posZ, 3, (double)target.width * 0.5D, 0.3D, (double)target.width * 0.5D, 0.02D);
            }
         }
         return true;
      }
      return false;
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
         }
         player.getFoodStats().addStats(this, stack);
      }
      return stack;
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 32;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.EAT;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (player.canEat(false)) {
         player.setActiveHand(hand);
         return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
      }
      return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
   }
}
