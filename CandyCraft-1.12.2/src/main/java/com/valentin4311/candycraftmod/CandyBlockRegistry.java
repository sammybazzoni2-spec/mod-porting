package com.valentin4311.candycraftmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/**
 * Creates, registers and configures every block of the mod (the construction code of the original
 * CandyCraft.doConfig) plus the item blocks and tile entities.
 */
public class CandyBlockRegistry {
   private static final List<Block> BLOCKS = new ArrayList<Block>();
   private static boolean initialized;

   private static <T extends Block> T c(T block, String unlocalizedName, String registryName, float hardness, float resistance, SoundType sound, boolean tab) {
      block.setTranslationKey(unlocalizedName);
      block.setRegistryName("candycraftmod", registryName);
      if (hardness < 0.0F) {
         block.setHardness(-1.0F);
      } else {
         block.setHardness(hardness);
      }

      if (resistance >= 0.0F) {
         block.setResistance(resistance);
      }

      sound(block, sound);
      if (tab) {
         block.setCreativeTab(CandyCraft.PuddingTabs);
      }

      BLOCKS.add(block);
      return block;
   }

   private static void light(Block block, float level) {
      block.setLightLevel(level);
   }

   private static void sound(Block block, SoundType sound) {
      try {
         ReflectionHelper.setPrivateValue(Block.class, block, sound, "blockSoundType", "field_149762_H");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void opacity(Block block, int opacity) {
      try {
         ReflectionHelper.setPrivateValue(Block.class, block, Integer.valueOf(opacity), "lightOpacity", "field_149786_r");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /** Creates all blocks and assigns them to the CandyCraft.* static fields. Must run before the items are created. */
   public static void init() {
      if (initialized) {
         return;
      }

      initialized = true;
      CandyCraft.PuddingBlock = c(new BlockPudding(), "B1", "b1", 0.6F, -1.0F, SoundType.CLOTH, true);
      CandyCraft.FlourBlock = c(new BlockBase(Material.GROUND), "B2", "b2", 0.6F, -1.0F, SoundType.CLOTH, true);
      CandyCraft.CandyPlanks = c(new BlockCandyPlanks(Material.WOOD), "B3", "b3", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyLog = c(new BlockCandyLog(), "B4", "b4", 2.0F, -1.0F, SoundType.WOOD, true);
      CandyCraft.CandyLeave = c(new BlockCandyLeave(), "B6", "b6", 0.2F, -1.0F, SoundType.PLANT, true);
      opacity(CandyCraft.CandyLeave, 1);
      CandyCraft.CandyLeave2 = c(new BlockCandyLeave2(), "B100", "b100", 0.2F, -1.0F, SoundType.PLANT, true);
      opacity(CandyCraft.CandyLeave2, 1);
      CandyCraft.CandySapling = c(new BlockCandySapling(), "B7", "b7", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.CandySoil = c(new BlockCandyFarmland(), "B8", "b8", 0.6F, -1.0F, SoundType.CLOTH, false);
      CandyCraft.TallCandyGrass = c(new BlockTallCandyGrass(), "B9", "b9", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.LicoriceOre = c(new BlockBase(Material.ROCK), "B14", "b14", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.CandyFence = c(new BlockTexturedFence("B3", "B3", Material.WOOD), "X0", "x0", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyStairs = c(new BlockCandyStairs(CandyCraft.CandyPlanks, 0), "X1", "x1", 3.0F, 5.0F, SoundType.WOOD, true);
      opacity(CandyCraft.CandyStairs, 0);
      CandyCraft.CandyStairs2 = c(new BlockCandyStairs(CandyCraft.CandyPlanks, 1), "XX1", "xx1", 3.0F, 5.0F, SoundType.WOOD, true);
      opacity(CandyCraft.CandyStairs2, 0);
      CandyCraft.CandyStairs3 = c(new BlockCandyStairs(CandyCraft.CandyPlanks, 2), "XXX1", "xxx1", 3.0F, 5.0F, SoundType.WOOD, true);
      opacity(CandyCraft.CandyStairs3, 0);
      CandyCraft.CandyHalfStep = c(new BlockCandyStep(false, "B3", "B3", Material.WOOD, 0), "XB3", "candyhalfslab", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyStep = c(new BlockCandyStep(true, "B3", "B3", Material.WOOD, 0), "XXB3", "candyslab", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyHalfStep2 = c(new BlockCandyStep(false, "B89", "B89", Material.WOOD, 4), "XB89", "candyhalfslab2", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyStep2 = c(new BlockCandyStep(true, "B89", "B89", Material.WOOD, 4), "XXB89", "candyslab2", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyHalfStep3 = c(new BlockCandyStep(false, "B90", "B90", Material.WOOD, 5), "XB90", "candyhalfslab3", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.CandyStep3 = c(new BlockCandyStep(true, "B90", "B90", Material.WOOD, 5), "XXB90", "candyslab3", 3.0F, 5.0F, SoundType.WOOD, true);
      CandyCraft.LicoriceBrick = c(new BlockBase(Material.ROCK), "B13", "b13", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.LicoriceBrickStairs = c(new BlockCandyStairs(CandyCraft.LicoriceBrick, 0), "X2", "x2", 3.0F, 5.0F, SoundType.STONE, true);
      opacity(CandyCraft.LicoriceBrickStairs, 0);
      CandyCraft.LicoriceHalfStep = c(new BlockCandyStep(false, "B13", "B13", Material.ROCK, 1), "X22", "licoricehalfslab", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.LicoriceStep = c(new BlockCandyStep(true, "B13", "B13", Material.ROCK, 1), "X22", "licoriceslab", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.LicoriceBlock = c(new BlockBase(Material.IRON), "B12", "b12", 5.0F, 10.0F, SoundType.METAL, true);
      CandyCraft.BarleyBlock = c(new BlockBarley(Material.WOOD), "BarleyBlock", "barleyblock", 1.0F, 2.0F, SoundType.WOOD, true);
      CandyCraft.BarleyFence = c(new BlockTexturedFence("B11", "B10", Material.WOOD), "BarleyFence", "barleyfence", 1.0F, 2.0F, SoundType.WOOD, true);
      CandyCraft.BarleyWall = c(new BlockTexturedWall("B11", "B10", CandyCraft.BarleyFence), "BarleyWall", "barleywall", 1.0F, 2.0F, SoundType.WOOD, true);
      CandyCraft.BarleyStairs = c(new BlockCandyTextureStairs(CandyCraft.BarleyBlock, "B11", "B10", Material.WOOD), "BarleyStairs", "barleystairs", 1.0F, 2.0F, SoundType.WOOD, true);
      opacity(CandyCraft.BarleyStairs, 0);
      CandyCraft.BarleyHalfStep = c(new BlockCandyStep(false, "B10", "B11", Material.WOOD, 2), "X21", "barleyhalfslab", 1.0F, 2.0F, SoundType.STONE, true);
      CandyCraft.BarleyStep = c(new BlockCandyStep(true, "B10", "B11", Material.WOOD, 2), "B10", "barleyslab", 1.0F, 2.0F, SoundType.STONE, true);
      CandyCraft.JellyOre = c(new BlockBase(Material.ROCK), "B24", "b24", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.JellyJump = c(new BlockJelly("B25", (double)2.0F), "B25", "b25", 3.0F, 2000.0F, CandyCraft.soundJellyFootstep, true);
      CandyCraft.JellyJumpHard = c(new BlockJelly("B28", (double)4.0F), "B28", "b28", 3.0F, 2000.0F, CandyCraft.soundJellyFootstep, true);
      CandyCraft.JellyJumpRelative = c(new BlockJelly("B29", (double)-1.0F), "B29", "b29", 3.0F, 2000.0F, CandyCraft.soundJellyFootstep, true);
      CandyCraft.LollipopBlock = c(new BlockLollipop(Material.PLANTS), "B34", "b34", 0.0F, 0.0F, SoundType.PLANT, true);
      CandyCraft.LollipopPlant = c(new BlockLollipopPlant(), "B33", "b33", 0.0F, 0.0F, SoundType.PLANT, false);
      CandyCraft.CaramelBlock = c(new BlockBase(Material.IRON), "B35", "b35", 2.0F, 2000.0F, SoundType.METAL, true);
      CandyCraft.SugarFactory = c(new BlockSugarFactory(Material.IRON, false), "B30", "b30", 2.0F, 5.0F, SoundType.METAL, true);
      CandyCraft.SugarFurnace = c(new BlockCandyFurnace(false), "B37", "b37", 5.0F, 10.0F, SoundType.METAL, true);
      CandyCraft.SugarFurnaceOn = c(new BlockCandyFurnace(true), "B38", "b38", 5.0F, 10.0F, SoundType.METAL, false);
      light(CandyCraft.SugarFurnaceOn, 0.875F);
      CandyCraft.CandyPortal = c(new BlockCandyPortal(), "B40", "b40", -1.0F, -1.0F, SoundType.GLASS, false);
      light(CandyCraft.CandyPortal, 0.75F);
      CandyCraft.SugarBlock = c(new BlockSugar(Material.SAND), "B39", "b39", 0.3F, -1.0F, SoundType.GROUND, true);
      CandyCraft.ChocolateStone = c(new BlockChocolateStone(), "B43", "b43", 1.5F, 10.0F, SoundType.STONE, true);
      CandyCraft.ChocolateCobbleStone = c(new BlockBase(Material.ROCK), "B44", "b44", 2.0F, 10.0F, SoundType.STONE, true);
      CandyCraft.CandyWaterLily = c(new BlockCandyWaterLily(), "B45", "candypad", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.DragiCrops = c(new BlockDragibus(), "B46", "b46", 0.0F, -1.0F, SoundType.PLANT, false);
      CandyCraft.PinkAlgae = c(new BlockPinkAlgae(Material.WATER), "B47", "b47", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.GreenAlgae = c(new BlockGreenAlgae(Material.WATER), "B48", "b48", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.CandyWorkbench = c(new BlockCandyWorkbench(), "B51", "b51", 2.5F, -1.0F, SoundType.WOOD, true);
      CandyCraft.CandyLadder = c(new BlockCandyLadder(), "B53", "b53", 2.5F, -1.0F, SoundType.LADDER, true);
      CandyCraft.CandyDoor = c(new BlockCandyDoor(Material.WOOD), "B55", "b55", 2.5F, -1.0F, SoundType.LADDER, false);
      CandyCraft.CandyFlower = c(new BlockCandyFlower(), "B56", "b56", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.CandyChest = c(new BlockCandyChest(4311), "B56X", "b56x", 2.5F, -1.0F, SoundType.WOOD, true);
      CandyCraft.HoneyOre = c(new BlockHoneyOre(Material.ROCK), "B57", "b57", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.HoneyTorch = c(new BlockCandyTorch(), "B58", "b58", 0.0F, -1.0F, SoundType.WOOD, true);
      light(CandyCraft.HoneyTorch, 0.9375F);
      CandyCraft.HoneyBlock = c(new BlockBase(Material.ROCK), "B59", "b59", 2.0F, -1.0F, SoundType.STONE, true);
      CandyCraft.HoneyLamp = c(new BlockBase(Material.GLASS), "B60", "b60", 1.0F, -1.0F, SoundType.GLASS, true);
      light(CandyCraft.HoneyLamp, 1.0F);
      CandyCraft.PEZOre = c(new BlockBase(Material.ROCK), "B61", "b61", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.PEZBlock = c(new BlockBase(Material.IRON), "B62", "b62", 5.0F, 10.0F, SoundType.METAL, true);
      CandyCraft.GrenadineFlow = c(new BlockCandyFlowing(Material.WATER), "B63", "b63", 100.0F, -1.0F, SoundType.STONE, false);
      CandyCraft.GrenadineStatic = c(new BlockCandyStill(Material.WATER), "B64", "b64", 100.0F, -1.0F, SoundType.STONE, false);
      CandyCraft.JawBreakerBlock = c(new BlockBase(Material.ROCK), "B65", "b65", -1.0F, 6000000.0F, SoundType.STONE, true);
      CandyCraft.PurpleJellyJump = c(new BlockJelly("B66", 2.1), "B66", "b66", 3.0F, 2000.0F, CandyCraft.soundJellyFootstep, false);
      light(CandyCraft.PurpleJellyJump, 0.8F);
      CandyCraft.CottonCandyBlock = c(new BlockBase(Material.CLOTH), "B67", "b67", 0.6F, -1.0F, SoundType.CLOTH, true);
      CandyCraft.JawBreakerLight = c(new BlockBase(Material.ROCK), "B17", "b17", -1.0F, 6000000.0F, SoundType.STONE, true);
      light(CandyCraft.JawBreakerLight, 0.7F);
      CandyCraft.CranberrySpikes = c(new BlockSpikes(2), "B68", "b68", 0.0F, -1.0F, SoundType.METAL, true);
      CandyCraft.CottonCandyStairs = c(new BlockCandyStairs(CandyCraft.CottonCandyBlock, 0), "X67", "x67", 0.6F, -1.0F, SoundType.CLOTH, true);
      opacity(CandyCraft.CottonCandyStairs, 0);
      CandyCraft.CottonCandyHalfStep = c(new BlockCandyStep(false, "B67", "B67", Material.CLOTH, 3), "XS67", "cottoncandyhalfslab", 3.0F, 5.0F, SoundType.CLOTH, true);
      CandyCraft.CottonCandyStep = c(new BlockCandyStep(true, "B67", "B67", Material.CLOTH, 3), "XDS67", "cottoncandyslab", 3.0F, 5.0F, SoundType.CLOTH, true);
      CandyCraft.CottonCandyBedBlock = c(new BlockCandyBed(), "B70", "b70", 0.2F, -1.0F, SoundType.CLOTH, false);
      CandyCraft.MintBlock = c(new BlockBase(Material.CLOTH), "B18", "b18", 1.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.RaspberryBlock = c(new BlockBase(Material.CLOTH), "B19", "b19", 1.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.JellySentryKeyHole = c(new BlockKeyHole(Material.ROCK, 0), "B75", "b75", -1.0F, 6000000.0F, SoundType.STONE, true);
      CandyCraft.JellyBossKeyHole = c(new BlockKeyHole(Material.ROCK, 1), "B75.0", "b75_0", -1.0F, 6000000.0F, SoundType.STONE, true);
      CandyCraft.SugarSpike = c(new BlockSpikes(4), "B20", "b20", 0.0F, -1.0F, SoundType.METAL, true);
      CandyCraft.BlockTeleporter = c(new BlockTeleporter(Material.ROCK), "B21", "b21", 3.0F, 2000.0F, SoundType.METAL, false);
      CandyCraft.CaramelGlass0 = c(new BlockCandyGlass(Material.GLASS, false, "B76"), "B76", "b76", 0.3F, -1.0F, SoundType.GLASS, true);
      CandyCraft.CaramelGlass1 = c(new BlockCandyGlass(Material.GLASS, false, "B77"), "B77", "b77", 0.5F, -1.0F, SoundType.GLASS, true);
      CandyCraft.CaramelGlass2 = c(new BlockCandyGlass(Material.GLASS, false, "B78"), "B78", "b78", 0.7F, -1.0F, SoundType.GLASS, true);
      CandyCraft.CaramelPane0 = c(new BlockCandyGlassPane("B76", "B22", Material.GLASS, false), "BP76", "bp76", 0.3F, -1.0F, SoundType.GLASS, true);
      CandyCraft.CaramelPane1 = c(new BlockCandyGlassPane("B77", "B22", Material.GLASS, false), "BP77", "bp77", 0.5F, -1.0F, SoundType.GLASS, true);
      CandyCraft.CaramelPane2 = c(new BlockCandyGlassPane("B78", "B22", Material.GLASS, false), "BP78", "bp78", 0.7F, -1.0F, SoundType.GLASS, true);
      CandyCraft.CottonCandyWeb = c(new BlockCandyWeb(), "B23", "b23", 4.0F, -1.0F, SoundType.STONE, true);
      opacity(CandyCraft.CottonCandyWeb, 1);
      CandyCraft.CherryBlock = c(new BlockCherry(Material.WOOD), "B81", "b81", 0.2F, -1.0F, SoundType.STONE, false);
      opacity(CandyCraft.CherryBlock, 1);
      CandyCraft.BananaSeaweeds = c(new BlockYellowAlgae(Material.WATER), "B82", "b82", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.NougatOre = c(new BlockNougat(), "B91", "b91", 3.0F, 5.0F, SoundType.STONE, true);
      CandyCraft.AdvancedSugarFactory = c(new BlockSugarFactory(Material.IRON, true), "B92", "b92", 2.0F, 5.0F, SoundType.METAL, true);
      CandyCraft.PoisonousFlower = c(new BlockCandyFlower(), "B93", "b93", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.NougatBlock = c(new BlockBase(Material.IRON), "B94", "b94", 1.0F, -1.0F, SoundType.METAL, true);
      CandyCraft.NougatHead = c(new BlockNougatHead(), "B95", "b95", 1.0F, -1.0F, SoundType.METAL, true);
      CandyCraft.BananaBlock = c(new BlockBase(Material.CLOTH), "B96", "b96", 1.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.ChewingGumBlock = c(new BlockChewingGum(Material.CLOTH), "B97", "b97", 1.0F, -1.0F, CandyCraft.soundJellyFootstep, true);
      CandyCraft.ChewingGumPlate = c(new BlockChewingGumPlate(Material.PLANTS), "B98", "b98", 1.0F, -1.0F, CandyCraft.soundJellyFootstep, true);
      CandyCraft.AlchemyTable = c(new BlockAlchemyTable(Material.ROCK), "T0", "t0", 1.0F, -1.0F, SoundType.METAL, true);
      CandyCraft.MarshmallowFlowerBlock = c(new BlockCandyWaterLily(), "B99", "candypad2", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.GrenadineBlock = c(new BlockGrenadine(Material.GLASS), "B101", "b101", 1.0F, -1.0F, SoundType.GLASS, true);
      CandyCraft.IceCream = c(new BlockIceCream(Material.CRAFTED_SNOW), "B102", "b102", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamStairs0 = c(new BlockCandyStairs(CandyCraft.IceCream, 0), "XX102_0", "xx102_0", 1.0F, -1.0F, SoundType.SNOW, true);
      opacity(CandyCraft.IceCreamStairs0, 0);
      CandyCraft.IceCreamStairs1 = c(new BlockCandyStairs(CandyCraft.IceCream, 1), "XX102_1", "xx102_1", 1.0F, -1.0F, SoundType.SNOW, true);
      opacity(CandyCraft.IceCreamStairs1, 0);
      CandyCraft.IceCreamStairs2 = c(new BlockCandyStairs(CandyCraft.IceCream, 2), "XX102_2", "xx102_2", 1.0F, -1.0F, SoundType.SNOW, true);
      opacity(CandyCraft.IceCreamStairs2, 0);
      CandyCraft.IceCreamStairs3 = c(new BlockCandyStairs(CandyCraft.IceCream, 3), "XX102_3", "xx102_3", 1.0F, -1.0F, SoundType.SNOW, true);
      opacity(CandyCraft.IceCreamStairs3, 0);
      CandyCraft.IceCreamHalfStep0 = c(new BlockCandyStep(false, "B102", "B102", Material.CRAFTED_SNOW, 6), "X102_0", "icecream0halfslab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamStep0 = c(new BlockCandyStep(true, "B102", "B102", Material.CRAFTED_SNOW, 6), "XXX102_0", "icecream0slab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamHalfStep1 = c(new BlockCandyStep(false, "B102_1", "B102_1", Material.CRAFTED_SNOW, 7), "X102_1", "icecream1halfslab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamStep1 = c(new BlockCandyStep(true, "B102_1", "B102_1", Material.CRAFTED_SNOW, 7), "XXX102_1", "icecream1slab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamHalfStep2 = c(new BlockCandyStep(false, "B102_2", "B102_2", Material.CRAFTED_SNOW, 8), "X102_2", "icecream2halfslab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamStep2 = c(new BlockCandyStep(true, "B102_2", "B102_2", Material.CRAFTED_SNOW, 8), "XXX102_2", "icecream2slab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamHalfStep3 = c(new BlockCandyStep(false, "B102_0", "B102_0", Material.CRAFTED_SNOW, 9), "X102_3", "icecream3halfslab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.IceCreamStep3 = c(new BlockCandyStep(true, "B102_0", "B102_0", Material.CRAFTED_SNOW, 9), "XXX102_3", "icecream3slab", 1.0F, -1.0F, SoundType.SNOW, true);
      CandyCraft.DragonEgg = c(new BlockEgg(false), "B104", "b104", 3.0F, 15.0F, SoundType.METAL, false);
      CandyCraft.BeetleBlockEgg = c(new BlockEgg(true), "B105", "b105", 3.0F, 15.0F, SoundType.METAL, false);
      CandyCraft.GoldenFlower = c(new BlockCandyFlower(), "B103", "b103", 0.0F, -1.0F, SoundType.PLANT, true);
      CandyCraft.LicoriceOre.setHarvestLevel("pickaxe", 1);
      CandyCraft.PEZOre.setHarvestLevel("pickaxe", 3);
      CandyCraft.HoneyOre.setHarvestLevel("pickaxe", 0);
      CandyCraft.ChocolateStone.setHarvestLevel("pickaxe", 0);
      CandyCraft.ChocolateCobbleStone.setHarvestLevel("pickaxe", 0);
      CandyCraft.SugarFurnace.setHarvestLevel("pickaxe", 0);
      CandyCraft.JellyOre.setHarvestLevel("pickaxe", 2);
      CandyCraft.NougatOre.setHarvestLevel("pickaxe", 2);
      CandyCraft.PuddingBlock.setHarvestLevel("shovel", 0);
      CandyCraft.FlourBlock.setHarvestLevel("shovel", 0);
   }

   public static void registerBlocks(RegistryEvent.Register<Block> event) {
      init();
      for (Block block : BLOCKS) {
         event.getRegistry().register(block);
      }
   }

   private static boolean isMulti(Block block) {
      return block == CandyCraft.CandyPlanks || block == CandyCraft.CandyLog || block == CandyCraft.CandyLeave || block == CandyCraft.CandyLeave2 || block == CandyCraft.CandySapling || block == CandyCraft.TallCandyGrass || block == CandyCraft.IceCream;
   }

   private static boolean skipItem(Block block) {
      return block == CandyCraft.GrenadineFlow || block == CandyCraft.GrenadineStatic || block == CandyCraft.CandyPortal || block == CandyCraft.CandyDoor || block == CandyCraft.CottonCandyBedBlock || block == CandyCraft.SugarFurnaceOn;
   }

   public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
      for (Block block : BLOCKS) {
         if (skipItem(block)) {
            continue;
         }

         ItemBlock item;
         if (block instanceof BlockSlab) {
            item = new ItemCandySlab(block);
         } else if (isMulti(block)) {
            item = new ItemCandyBlock(block);
         } else if (block == CandyCraft.CandyWaterLily) {
            item = new ItemCandyPad(block);
         } else if (block == CandyCraft.MarshmallowFlowerBlock) {
            item = new ItemCandyFlowerPad(block);
         } else {
            item = new ItemBlock(block);
         }

         item.setRegistryName(block.getRegistryName());
         event.getRegistry().register(item);
         CandyCraft.itemList.add(item);
      }
   }

   public static void registerTileEntities() {
      GameRegistry.registerTileEntity(TileEntityCandyChest.class, new ResourceLocation("candycraftmod", "candychest"));
      GameRegistry.registerTileEntity(TileEntityTeleporter.class, new ResourceLocation("candycraftmod", "teleporterblock"));
      GameRegistry.registerTileEntity(TileEntitySugarFactory.class, new ResourceLocation("candycraftmod", "sugarfactory"));
      GameRegistry.registerTileEntity(TileEntitySugarFurnace.class, new ResourceLocation("candycraftmod", "sugarfurnace"));
      GameRegistry.registerTileEntity(TileEntityAlchemy.class, new ResourceLocation("candycraftmod", "alchemytable"));
      GameRegistry.registerTileEntity(TileEntityEgg.class, new ResourceLocation("candycraftmod", "hatchingegg"));
   }

   public static List<Block> getBlocks() {
      return BLOCKS;
   }
}
