package com.valentin4311.candycraftmod;

import java.util.HashMap;
import java.util.Random;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class AlchemyRecipes {
   private static HashMap<Item, Potion> recipeList = new HashMap<Item, Potion>();
   private static Potion[] mallusList;
   private static Random rand = new Random();

   public AlchemyRecipes() {
      recipeList.put(CandyCraft.CandiedCherry, MobEffects.INSTANT_HEALTH);
      recipeList.put(CandyCraft.PEZDust, MobEffects.FIRE_RESISTANCE);
      recipeList.put(CandyCraft.PEZ, MobEffects.RESISTANCE);
      recipeList.put(CandyCraft.BarleySugar, MobEffects.REGENERATION);
      recipeList.put(CandyCraft.CottonCandy, MobEffects.SATURATION);
      recipeList.put(CandyCraft.Gummy, MobEffects.JUMP_BOOST);
      recipeList.put(CandyCraft.HoneyShard, MobEffects.HASTE);
      recipeList.put(CandyCraft.Waffle, MobEffects.ABSORPTION);
      recipeList.put(Item.getItemFromBlock(CandyCraft.CandyFlower), MobEffects.HASTE);
      recipeList.put(Item.getItemFromBlock(CandyCraft.BananaBlock), MobEffects.WATER_BREATHING);
      recipeList.put(Item.getItemFromBlock(CandyCraft.MintBlock), MobEffects.WATER_BREATHING);
      recipeList.put(Item.getItemFromBlock(CandyCraft.RaspberryBlock), MobEffects.WATER_BREATHING);
      recipeList.put(CandyCraft.SugarCrystal, MobEffects.INVISIBILITY);
      recipeList.put(Item.getItemFromBlock(CandyCraft.PoisonousFlower), MobEffects.POISON);
      recipeList.put(CandyCraft.MarshmallowFlower, MobEffects.SPEED);
      recipeList.put(CandyCraft.Dragibus, MobEffects.REGENERATION);
      recipeList.put(CandyCraft.Lollipop, MobEffects.INSTANT_HEALTH);
      recipeList.put(CandyCraft.LollipopSeeds, MobEffects.WEAKNESS);
      recipeList.put(CandyCraft.GummyBall, MobEffects.SLOWNESS);
      recipeList.put(CandyCraft.CaramelBucket, MobEffects.STRENGTH);
      recipeList.put(CandyCraft.Licorice, MobEffects.NIGHT_VISION);
      recipeList.put(CandyCraft.HoneyComb, MobEffects.SPEED);
      recipeList.put(CandyCraft.CranberryFish, MobEffects.INSTANT_DAMAGE);
      recipeList.put(CandyCraft.ChewingGum, MobEffects.MINING_FATIGUE);
      recipeList.put(CandyCraft.WaffleNugget, MobEffects.HUNGER);
      recipeList.put(CandyCraft.NougatPowder, MobEffects.NAUSEA);
      recipeList.put(Items.COOKIE, MobEffects.BLINDNESS);
      recipeList.put(Items.SUGAR, MobEffects.HUNGER);
   }

   public static Potion getPotionIdForItem(ItemStack item) {
      if (mallusList == null) {
         mallusList = new Potion[]{MobEffects.HUNGER};
      }
      return item != null && !item.isEmpty() && recipeList.containsKey(item.getItem()) ? recipeList.get(item.getItem()) : mallusList[rand.nextInt(mallusList.length)];
   }
}
