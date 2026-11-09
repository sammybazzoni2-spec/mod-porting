package com.thecramp.armor;

import com.thecramp.Main.MainRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class FinnHat extends ItemArmor {
   public FinnHat(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot slot) {
      super(armorMaterial, renderIndex, slot);
   }

   @Override
   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      return !stack.getItem().equals(MainRegistry.FinnHat) && !stack.getItem().equals(MainRegistry.Sweater) ? null : "ooocraft:textures/armor/finnhat_1.png";
   }

   @Override
   public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
      if (itemstack.getItem() == MainRegistry.FinnHat) {
         player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100));
         player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100));
         player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100));
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100));
         if (player.isInWater()) {
            player.jump();
            player.fallDistance = 0.0F;
            if (!world.isRemote && player.ticksExisted % 40 == 0) {
               player.sendMessage(new TextComponentTranslation("msg.finn.txt"));
            }
         }
      }
      if (itemstack.getItem() == MainRegistry.Sweater) {
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 3));
         player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
      }
   }
}
