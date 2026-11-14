package com.valentin4311.candycraftmod;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The 8-digit effect code is stored in the NBT tag "MetaSystem" (int). Potion ids are the 1.12 registry ids
 * (identical to the 1.7.10 ids for the vanilla effects).
 */
public class ItemGrenadineCandy extends ItemFood {
   public ItemGrenadineCandy() {
      super(0, false);
   }

   private static String metaString(ItemStack stack) {
      String s = String.valueOf(stack.getItemDamage());
      while (s.length() < 8) {
         s = "0" + s;
      }
      return s;
   }

   private PotionEffect[] buildEffects(ItemStack stack) {
      String metaString = metaString(stack);
      PotionEffect[] tabl = new PotionEffect[4];
      for (int i = 0; i < 4; ++i) {
         try {
            int j = Integer.valueOf(metaString.substring(i * 2, i * 2 + 2));
            Potion potion = Potion.getPotionById(j);
            if (potion != null) {
               int k = this.containPotion(tabl, j);
               if (k != -1) {
                  tabl[k] = this.upgradePotion(tabl[k]);
               } else {
                  tabl[i] = new PotionEffect(potion, 1200, 0);
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return tabl;
   }

   @Override
   protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
      if (world.isRemote) {
         return;
      }
      player.clearActivePotions();
      PotionEffect[] tabl = this.buildEffects(stack);
      for (int l = 0; l < 4; ++l) {
         try {
            if (tabl[l] != null && tabl[l].getPotion() != null) {
               player.addPotionEffect(tabl[l].getPotion().isInstant() ? new PotionEffect(tabl[l].getPotion(), 1, tabl[l].getAmplifier()) : tabl[l]);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   @Override
   public int getDamage(ItemStack stack) {
      return stack.hasTagCompound() ? stack.getTagCompound().getInteger("MetaSystem") : 0;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("");
      tooltip.add("\u00a76" + I18n.format("Gui.GrenadineCandy.Potion"));
      PotionEffect[] tabl = this.buildEffects(stack);
      for (int l = 0; l < 4; ++l) {
         try {
            if (tabl[l] != null && tabl[l].getPotion() != null) {
               Potion p = tabl[l].getPotion();
               tooltip.add("\u00a7e" + I18n.format(p.getName()) + (tabl[l].getAmplifier() != 0 ? " " + I18n.format("enchantment.level." + (tabl[l].getAmplifier() + 1)) : "") + (p.isInstant() ? "" : " (" + Potion.getPotionDurationString(tabl[l], 1.0F) + ")"));
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public PotionEffect upgradePotion(PotionEffect potion) {
      if (potion.getAmplifier() == 0) {
         return potion.getDuration() == 1200 ? new PotionEffect(potion.getPotion(), 2400, 0) : new PotionEffect(potion.getPotion(), 2400, 1);
      } else {
         return new PotionEffect(potion.getPotion(), 3600, 1);
      }
   }

   public int containPotion(PotionEffect[] tab, int pot) {
      for (int i = 0; i < tab.length; ++i) {
         if (tab[i] != null && pot == Potion.getIdFromPotion(tab[i].getPotion())) {
            return i;
         }
      }
      return -1;
   }

   /** Tint for layer 1..4 (layer 0 is the plain candy). */
   @SideOnly(Side.CLIENT)
   public static int getColor(ItemStack stack, int tintIndex) {
      if (tintIndex > 0) {
         String metaString = metaString(stack);
         try {
            int j = Integer.valueOf(metaString.substring((tintIndex - 1) * 2, (tintIndex - 1) * 2 + 2));
            Potion p = Potion.getPotionById(j);
            if (p != null) {
               return p.getLiquidColor();
            }
         } catch (Exception e) {
         }
      }
      return 16777215;
   }
}
