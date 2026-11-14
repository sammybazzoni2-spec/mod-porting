package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDreamArmor extends ItemArmor {
   private static final String P = "candycraftmod:textures/models/armor/";

   private static EntityEquipmentSlot slotFor(int type) {
      switch (type) {
         case 0: return EntityEquipmentSlot.HEAD;
         case 1: return EntityEquipmentSlot.CHEST;
         case 2: return EntityEquipmentSlot.LEGS;
         default: return EntityEquipmentSlot.FEET;
      }
   }

   public ItemDreamArmor(ItemArmor.ArmorMaterial material, int armorType) {
      super(material, 0, slotFor(armorType));
   }

   @Override
   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      Item i = stack.getItem();
      if (i == CandyCraft.LicoriceHelmet || i == CandyCraft.LicoricePlate || i == CandyCraft.LicoriceBoots) {
         return P + "armor_licorice_2.png";
      } else if (i == CandyCraft.LicoriceLeggings) {
         return P + "armor_licorice_1.png";
      } else if (i == CandyCraft.HoneyHelmet || i == CandyCraft.HoneyPlate || i == CandyCraft.HoneyBoots) {
         return P + "armor_honey_2.png";
      } else if (i == CandyCraft.HoneyLeggings) {
         return P + "armor_honey_1.png";
      } else if (i == CandyCraft.PEZHelmet || i == CandyCraft.PEZPlate || i == CandyCraft.PEZBoots) {
         return P + "armor_pez_2.png";
      } else if (i == CandyCraft.PEZLeggings) {
         return P + "armor_pez_1.png";
      } else if (i == CandyCraft.JellyCrown) {
         return P + "armor_crown.png";
      } else if (i == CandyCraft.WaterMask) {
         return P + "armor_mask.png";
      } else {
         return i == CandyCraft.FallBoots ? P + "armor_boots.png" : "candycraftmod:textures/models/armor/empty.png";
      }
   }

   @Override
   @SideOnly(Side.CLIENT)
   public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot, ModelBiped def) {
      return slot == EntityEquipmentSlot.HEAD && this == CandyCraft.JellyCrown && ClientProxy.crown != null ? ClientProxy.crown : null;
   }
}
