package com.thecramp.armor;

import com.thecramp.Main.MainRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class IceCrown extends ItemArmor {
   public IceCrown(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot slot) {
      super(armorMaterial, renderIndex, slot);
   }

   @Override
   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      return stack.getItem().equals(MainRegistry.IceCrown) ? "ooocraft:textures/armor/icecrown_1.png" : null;
   }

   @Override
   public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
      ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
      if (!helmet.isEmpty()) {
         if (helmet.getItem() == MainRegistry.IceCrown) {
            player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100));
            if (player.isSneaking()) {
               player.jump();
               player.fallDistance = 0.0F;
            }
            if (player.isInWater()) {
               player.jump();
               player.fallDistance = 0.0F;
            }
         }
      }
   }
}
