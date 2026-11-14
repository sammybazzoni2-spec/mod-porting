package com.valentin4311.candycraftmod;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CandyFurnaceRecipe {
   private static CandyFurnaceRecipe smeltingBase;
   private Map<ItemStack, ItemStack> smeltingList = new HashMap<ItemStack, ItemStack>();
   private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

   public static final synchronized CandyFurnaceRecipe smelting() {
      if (smeltingBase == null) {
         smeltingBase = new CandyFurnaceRecipe();
      }
      return smeltingBase;
   }

   private CandyFurnaceRecipe() {
      this.addSmeltingRecipeForBlock(CandyCraft.FlourBlock, new ItemStack(CandyCraft.PuddingBlock), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.LicoriceOre, new ItemStack(CandyCraft.Licorice), 1.0F);
      this.addSmeltingRecipeForBlock(CandyCraft.JellyOre, new ItemStack(CandyCraft.JellyJump), 1.0F);
      this.addSmeltingRecipeForBlock(CandyCraft.JellyJump, new ItemStack(CandyCraft.JellyJumpHard), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.JellyJumpHard, new ItemStack(CandyCraft.JellyJumpRelative), 0.7F);
      this.addSmelting(CandyCraft.CaramelBucket, new ItemStack(CandyCraft.CaramelBlock), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.SugarBlock, new ItemStack(CandyCraft.CaramelBlock), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.ChocolateCobbleStone, new ItemStack(CandyCraft.ChocolateStone), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.HoneyBlock, new ItemStack(CandyCraft.HoneyLamp), 0.7F);
      this.addSmelting(CandyCraft.Gummy, new ItemStack(CandyCraft.HotGummy), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.PEZOre, new ItemStack(CandyCraft.PEZ), 1.0F);
      this.addSmelting(CandyCraft.CranberryFish, new ItemStack(CandyCraft.CranberryFishCooked), 0.7F);
      this.addSmeltingRecipeForBlock(CandyCraft.CaramelBlock, new ItemStack(CandyCraft.CaramelGlass0), 0.7F);
   }

   public void addSmeltingRecipeForBlock(Block block, ItemStack result, float xp) {
      this.addSmelting(Item.getItemFromBlock(block), result, xp);
   }

   public void addSmelting(Item item, ItemStack result, float xp) {
      this.addSmeltingRecipe(new ItemStack(item, 1, 32767), result, xp);
   }

   public void addSmeltingRecipe(ItemStack input, ItemStack result, float xp) {
      this.smeltingList.put(input, result);
      this.experienceList.put(result, xp);
   }

   public ItemStack getSmeltingResult(ItemStack input) {
      if (input == null || input.isEmpty()) {
         return ItemStack.EMPTY;
      }
      for (Map.Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
         if (this.compareItemStacks(input, entry.getKey())) {
            return entry.getValue();
         }
      }
      return ItemStack.EMPTY;
   }

   private boolean compareItemStacks(ItemStack a, ItemStack b) {
      return b.getItem() == a.getItem() && (b.getMetadata() == 32767 || b.getMetadata() == a.getMetadata());
   }

   public Map<ItemStack, ItemStack> getSmeltingList() {
      return this.smeltingList;
   }

   public float getSmeltingExperience(ItemStack stack) {
      float ret = stack.getItem().getSmeltingExperience(stack);
      if (ret != -1.0F) {
         return ret;
      }
      for (Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
         if (this.compareItemStacks(stack, entry.getKey())) {
            return entry.getValue();
         }
      }
      return 0.0F;
   }
}
