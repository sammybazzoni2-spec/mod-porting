package com.valentin4311.candycraftmod;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.valentin4311.candycraftmod.CandyCraft.*;

/** Crafting recipes of the original DoCraft(); registered from the IRecipe registry event. */
public class CandyRecipes {
   private static Map<String, Integer> counters = new HashMap<String, Integer>();

   private static ResourceLocation name(ItemStack out) {
      String base = out.getItem().getRegistryName().getPath();
      Integer n = counters.get(base);
      n = n == null ? 0 : n + 1;
      counters.put(base, n);
      return new ResourceLocation("candycraftmod", base + "_" + n);
   }

   private static void shaped(ItemStack out, Object... params) {
      GameRegistry.addShapedRecipe(name(out), null, out, params);
   }

   private static void shapeless(ItemStack out, Object... ingredients) {
      net.minecraft.item.crafting.Ingredient[] ing = new net.minecraft.item.crafting.Ingredient[ingredients.length];
      for (int i = 0; i < ingredients.length; ++i) {
         ing[i] = net.minecraft.item.crafting.Ingredient.fromStacks((ItemStack)ingredients[i]);
      }
      GameRegistry.addShapelessRecipe(name(out), null, out, ing);
   }

   public static void register() {
      counters.clear();
      shapeless(new ItemStack(Items.SUGAR, 4), new ItemStack(SugarBlock));
      shapeless(new ItemStack(CandyPlanks, 4, 0), new ItemStack(CandyLog, 1, 0));
      shapeless(new ItemStack(CandyPlanks, 4, 1), new ItemStack(CandyLog, 1, 1));
      shapeless(new ItemStack(CandyPlanks, 4, 2), new ItemStack(CandyLog, 1, 2));
      shapeless(new ItemStack(CandyPlanks, 4, 3), new ItemStack(CandyLog, 1, 3));
      shapeless(new ItemStack(Items.STICK, 1), new ItemStack(CandyStick));
      shapeless(new ItemStack(HoneyShard, 9), new ItemStack(HoneyBlock));
      shaped(new ItemStack(CandyFence, 2), "PPP", "PPP", 'P', new ItemStack(CandyStick));
      shaped(new ItemStack(CandyStairs, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(CandyPlanks, 1, 0));
      shaped(new ItemStack(CandyStairs2, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(CandyPlanks, 1, 1));
      shaped(new ItemStack(CandyStairs3, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(CandyPlanks, 1, 2));
      shaped(new ItemStack(CandyHalfStep, 6), "PPP", 'P', new ItemStack(CandyPlanks, 1, 0));
      shaped(new ItemStack(CandyHalfStep2, 6), "PPP", 'P', new ItemStack(CandyPlanks, 1, 1));
      shaped(new ItemStack(CandyHalfStep3, 6), "PPP", 'P', new ItemStack(CandyPlanks, 1, 2));
      shaped(new ItemStack(LicoriceBrick, 3), "PP", "PP", 'P', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceBlock, 1), "PPP", "PPP", "PPP", 'P', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceBrickStairs, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(LicoriceBrick));
      shaped(new ItemStack(LicoriceHalfStep, 6), "PPP", 'P', new ItemStack(LicoriceBrick));
      shaped(new ItemStack(BarleyBlock, 2), "PP", "PP", 'P', new ItemStack(BarleySugar));
      shaped(new ItemStack(BarleyFence, 2), "PPP", "PPP", 'P', new ItemStack(BarleySugar));
      shaped(new ItemStack(BarleyStairs, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(BarleyBlock));
      shaped(new ItemStack(BarleyHalfStep, 6), "PPP", 'P', new ItemStack(BarleyBlock));
      shaped(new ItemStack(BarleyWall, 2), "PPP", "PPP", 'P', new ItemStack(BarleyBlock));
      shaped(new ItemStack(SugarFactory, 2), "PSP", "SBS", "PSP", 'P', new ItemStack(Licorice), 'S', new ItemStack(CandyStick), 'B', new ItemStack(BarleyWall));
      shaped(new ItemStack(SugarFurnace), "PPP", "P P", "PPP", 'P', new ItemStack(Licorice));
      shaped(new ItemStack(SugarBlock, 1), "PP", "PP", 'P', new ItemStack(Items.SUGAR));
      shaped(new ItemStack(LicoriceSword, 1), "L", "L", "P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceShovel, 1), "L", "P", "P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoricePickAxe, 1), "LLL", " P ", " P ", 'P', new ItemStack(CandyStick), 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceAxe, 1), "LL", "LP", " P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceHoe, 1), "LL", " P", " P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceHelmet, 1), "LLL", "L L", 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoricePlate, 1), "L L", "LLL", "LLL", 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceLeggings, 1), "LLL", "L L", "L L", 'L', new ItemStack(Licorice));
      shaped(new ItemStack(LicoriceBoots, 1), "L L", "L L", 'L', new ItemStack(Licorice));
      shaped(new ItemStack(DragibusStick, 1), "L ", " D", 'L', new ItemStack(Items.FISHING_ROD), 'D', new ItemStack(Dragibus));
      shaped(new ItemStack(Fork, 1), "L  ", " L ", "  L", 'L', new ItemStack(Items.IRON_INGOT));
      shaped(new ItemStack(CandyStick, 4), "L", "L", 'L', CandyPlanks);
      shaped(new ItemStack(CandyLadder, 3), "L L", "LLL", "L L", 'L', new ItemStack(CandyStick));
      shaped(new ItemStack(CandyDoorI), "LL", "LL", "LL", 'L', CandyPlanks);
      shaped(new ItemStack(CandyWorkbench), "LL", "LL", 'L', CandyPlanks);
      shaped(new ItemStack(CandyChest, 1), "PPP", "P P", "PPP", 'P', CandyPlanks);
      shaped(new ItemStack(HoneyTorch, 4), "P", "O", 'P', new ItemStack(HoneyShard), 'O', new ItemStack(CandyStick));
      shaped(new ItemStack(HoneyBlock, 1), "PPP", "PPP", "PPP", 'P', new ItemStack(HoneyShard));
      shaped(new ItemStack(HoneyHelmet, 1), "LLL", "L L", 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyPlate, 1), "L L", "LLL", "LLL", 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyLeggings, 1), "LLL", "L L", "L L", 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyBoots, 1), "L L", "L L", 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneySword, 1), "L", "L", "P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyShovel, 1), "L", "P", "P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyPickAxe, 1), "LLL", " P ", " P ", 'P', new ItemStack(CandyStick), 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyAxe, 1), "LL", "LP", " P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyHoe, 1), "LL", " P", " P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(HoneyComb));
      shaped(new ItemStack(HoneyArrow, 4), "L", "P", "O", 'P', new ItemStack(CandyStick), 'L', new ItemStack(HoneyShard), 'O', new ItemStack(BarleySugar));
      shapeless(new ItemStack(GummyBall), new ItemStack(Gummy));
      shapeless(new ItemStack(PEZDust, 9), new ItemStack(PEZ));
      shaped(new ItemStack(PEZ, 1), "PPP", "PPP", "PPP", 'P', new ItemStack(PEZDust));
      shaped(new ItemStack(PEZBlock, 1), "PPP", "PPP", "PPP", 'P', new ItemStack(PEZ));
      shaped(new ItemStack(PEZHelmet, 1), "LLL", "L L", 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZPlate, 1), "L L", "LLL", "LLL", 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZLeggings, 1), "LLL", "L L", "L L", 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZBoots, 1), "L L", "L L", 'L', new ItemStack(PEZ));
      shaped(new ItemStack(Items.BUCKET), "L L", " L ", 'L', new ItemStack(PEZDust));
      shaped(new ItemStack(Items.SHEARS), " #", "# ", '#', PEZDust);
      shaped(new ItemStack(CottonCandyBlock, 4), "PP", "PP", 'P', new ItemStack(CottonCandy));
      shaped(new ItemStack(Fork, 1), "L  ", " L ", "  L", 'L', new ItemStack(PEZDust));
      shaped(new ItemStack(CranberrySpikes, 2), "LL", 'L', new ItemStack(CranberryScale));
      shaped(new ItemStack(CottonCandyStairs, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(CottonCandyBlock));
      shaped(new ItemStack(CottonCandyHalfStep, 6), "PPP", 'P', new ItemStack(CottonCandyBlock));
      shaped(new ItemStack(CottonCandyBed, 1), "PPP", "OOO", 'P', new ItemStack(CottonCandyBlock), 'O', CandyPlanks);
      shaped(new ItemStack(MintBlock, 1), "PP", "PP", 'P', new ItemStack(GreenAlgae));
      shaped(new ItemStack(RaspberryBlock, 1), "PP", "PP", 'P', new ItemStack(PinkAlgae));
      shaped(new ItemStack(WaterMask, 1), "PPP", "POP", 'P', new ItemStack(SugarCrystal), 'O', new ItemStack(CranberryScale));
      shaped(new ItemStack(FallBoots, 1), "P P", "O O", 'P', new ItemStack(SugarCrystal), 'O', new ItemStack(JellyJumpRelative));
      shaped(new ItemStack(PEZSword, 1), "L", "L", "P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZShovel, 1), "L", "P", "P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZPickAxe, 1), "LLL", " P ", " P ", 'P', new ItemStack(CandyStick), 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZAxe, 1), "LL", "LP", " P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(PEZ));
      shaped(new ItemStack(PEZHoe, 1), "LL", " P", " P", 'P', new ItemStack(CandyStick), 'L', new ItemStack(PEZ));
      shaped(new ItemStack(SugarSpike, 8), "LL", 'L', new ItemStack(SugarCrystal));
      shapeless(new ItemStack(CaramelGlass1, 1), new ItemStack(CaramelGlass0));
      shapeless(new ItemStack(CaramelGlass2, 1), new ItemStack(CaramelGlass1));
      shaped(new ItemStack(CaramelPane0, 16), "PPP", "PPP", 'P', new ItemStack(CaramelGlass0));
      shaped(new ItemStack(CaramelPane1, 16), "PPP", "PPP", 'P', new ItemStack(CaramelGlass1));
      shaped(new ItemStack(CaramelPane2, 16), "PPP", "PPP", 'P', new ItemStack(CaramelGlass2));
      shapeless(new ItemStack(CaramelPane1, 1), new ItemStack(CaramelPane0));
      shapeless(new ItemStack(CaramelPane2, 1), new ItemStack(CaramelPane1));
      shaped(new ItemStack(Waffle, 1), "PP", "PP", "PP", 'P', new ItemStack(WaffleNugget));
      shaped(new ItemStack(CandySword, 1), "L", "L", "P", 'P', new ItemStack(CandyStick), 'L', CandyPlanks);
      shaped(new ItemStack(CandyShovel, 1), "L", "P", "P", 'P', new ItemStack(CandyStick), 'L', CandyPlanks);
      shaped(new ItemStack(CandyPickAxe, 1), "LLL", " P ", " P ", 'P', new ItemStack(CandyStick), 'L', CandyPlanks);
      shaped(new ItemStack(CandyAxe, 1), "LL", "LP", " P", 'P', new ItemStack(CandyStick), 'L', CandyPlanks);
      shaped(new ItemStack(CandyHoe, 1), "LL", " P", " P", 'P', new ItemStack(CandyStick), 'L', CandyPlanks);
      shaped(new ItemStack(AdvancedSugarFactory, 1), "LPL", "POP", "LPL", 'P', new ItemStack(CandiedCherry), 'L', new ItemStack(JellyJumpHard), 'O', new ItemStack(SugarFactory));
      shaped(new ItemStack(AlchemyTable, 1), "LPL", "POP", "LPL", 'P', CandyLog, 'L', new ItemStack(PEZ), 'O', new ItemStack(CaramelGlass0));
      shaped(new ItemStack(AlchemyTable, 1), "LPL", "POP", "LPL", 'P', CandyLog, 'L', new ItemStack(PEZ), 'O', new ItemStack(CaramelGlass1));
      shaped(new ItemStack(AlchemyTable, 1), "LPL", "POP", "LPL", 'P', CandyLog, 'L', new ItemStack(PEZ), 'O', new ItemStack(CaramelGlass2));
      shaped(new ItemStack(Items.COOKIE, 2), "PCP", 'P', new ItemStack(FlourBlock), 'C', new ItemStack(Items.DYE, 1, 3));
      shaped(new ItemStack(Dynamite, 3), "PCP", "PCP", "PCP", 'P', new ItemStack(WaffleNugget), 'C', new ItemStack(NougatPowder));
      shapeless(new ItemStack(GlueDynamite, 1), new ItemStack(Dynamite), new ItemStack(ChewingGum));
      shaped(new ItemStack(NougatBlock, 1), "PPP", "PPP", "PPP", 'P', new ItemStack(NougatPowder));
      shaped(new ItemStack(BananaBlock, 1), "PP", "PP", 'P', new ItemStack(BananaSeaweeds));
      shaped(new ItemStack(ChewingGumPlate, 1), "PP", 'P', new ItemStack(ChewingGum));
      shaped(new ItemStack(ChewingGumBlock, 1), "PP", "PP", 'P', new ItemStack(ChewingGum));
      shaped(new ItemStack(IceCreamStairs0, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(IceCream, 1, 0));
      shaped(new ItemStack(IceCreamStairs1, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(IceCream, 1, 1));
      shaped(new ItemStack(IceCreamStairs2, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(IceCream, 1, 2));
      shaped(new ItemStack(IceCreamStairs3, 4), "P  ", "PP ", "PPP", 'P', new ItemStack(IceCream, 1, 3));
      shaped(new ItemStack(IceCreamHalfStep0, 6), "PPP", 'P', new ItemStack(IceCream, 1, 0));
      shaped(new ItemStack(IceCreamHalfStep1, 6), "PPP", 'P', new ItemStack(IceCream, 1, 1));
      shaped(new ItemStack(IceCreamHalfStep2, 6), "PPP", 'P', new ItemStack(IceCream, 1, 2));
      shaped(new ItemStack(IceCreamHalfStep3, 6), "PPP", 'P', new ItemStack(IceCream, 1, 3));
      shaped(new ItemStack(HoneyBolt, 1), "  P", " P ", "C  ", 'P', new ItemStack(HoneyShard), 'C', new ItemStack(BarleySugar));
      shaped(new ItemStack(CaramelCrossbow, 1), "P  ", " C ", "  C", 'P', new ItemStack(CaramelBow), 'C', new ItemStack(CaramelBlock));
   }

   public static void registerSmelting() {
      GameRegistry.addSmelting(CandyCraft.LicoriceOre, new ItemStack(CandyCraft.Licorice), 2.0F);
   }
}
