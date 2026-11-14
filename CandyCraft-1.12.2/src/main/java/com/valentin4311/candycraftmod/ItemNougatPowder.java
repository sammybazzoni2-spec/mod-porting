package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNougatPowder extends ItemFood {
   public ItemNougatPowder(int amount, boolean isWolfFood) {
      super(amount, isWolfFood);
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
      ItemStack result = super.onItemUseFinish(stack, world, entity);
      if (!world.isRemote && world.rand.nextInt(4) == 0) {
         world.createExplosion((net.minecraft.entity.Entity)null, entity.posX, entity.posY, entity.posZ, 1.0F, true);
      }
      return result;
   }
}
