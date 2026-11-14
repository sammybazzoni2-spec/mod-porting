package com.valentin4311.candycraftmod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
   modid = "candycraftmod",
   name = "candycraftmod",
   version = "Beta 1.3",
   acceptedMinecraftVersions = "[1.12.2]"
)
@Mod.EventBusSubscriber(modid = "candycraftmod")
public class CandyCraft {
   public static final String version = "Beta 1.3";
   public static boolean shouldUpdate = false;
   @Instance("candycraftmod")
   public static CandyCraft modInstance;
   @SidedProxy(
      clientSide = "com.valentin4311.candycraftmod.ClientProxy",
      serverSide = "com.valentin4311.candycraftmod.CommonProxy"
   )
   public static CommonProxy proxy;
   CandyConfiguration config;
   public static ClientTick clientTicker;
   public static ServerTick serverTicker;
   public static CreativeTabs PuddingTabs = new CreativeCandyTabs("CandyCraft");
   private GuiHandlerCandyCraft guiHandler = new GuiHandlerCandyCraft();
   public static ArrayList descriptionList = new ArrayList();
   public static ArrayList<Item> itemList = new ArrayList<Item>();
   private static final List<Item> ownItems = new ArrayList<Item>();
   public static WorldTypeCandy candyWorldType;
   public static int idDimension = 23;
   public static int idDimension2 = 24;
   public static Biome CandyMountains;
   public static Biome CandyHellMountains;
   public static Biome CandyPlains;
   public static Biome CandyForest;
   public static Biome CandyColdForest;
   public static Biome CandyRiver;
   public static Biome CandyOcean;
   public static Biome CandyEnchantedForest;
   public static Biome CandyHellForest;
   public static Biome CandyFrostPlains;
   public static Biome CandyVoid;
   static Item.ToolMaterial LicoriceMaterial;
   static ItemArmor.ArmorMaterial LicoriceArmorMaterial;
   static Item.ToolMaterial HoneyMaterial;
   static ItemArmor.ArmorMaterial HoneyArmorMaterial;
   static Item.ToolMaterial PEZMaterial;
   static ItemArmor.ArmorMaterial PEZArmorMaterial;
   static ItemArmor.ArmorMaterial UselessMaterial;
   public static final CandyStepSound soundJellyFootstep = new CandyStepSound("jelly", 0.7F, 0.6F);
   public static final FluidGrenadine fluid = (FluidGrenadine)(new FluidGrenadine("Grenadine")).setViscosity(1500).setDensity(800);
   public static Block PuddingBlock;
   public static Block FlourBlock;
   public static Block CandyPlanks;
   public static Block CandyLog;
   public static Block CandyLeave;
   public static Block CandyLeave2;
   public static Block CandySapling;
   public static Block CandySoil;
   public static BlockTallCandyGrass TallCandyGrass;
   public static Block LicoriceOre;
   public static Block CandyFence;
   public static Block CandyStairs;
   public static BlockSlab CandyHalfStep;
   public static BlockSlab CandyStep;
   public static BlockSlab CandyHalfStep2;
   public static BlockSlab CandyStep2;
   public static BlockSlab CandyHalfStep3;
   public static BlockSlab CandyStep3;
   public static Block LicoriceBrick;
   public static Block LicoriceBrickStairs;
   public static BlockSlab LicoriceHalfStep;
   public static BlockSlab LicoriceStep;
   public static Block LicoriceBlock;
   public static Block BarleyBlock;
   public static Block BarleyFence;
   public static Block BarleyWall;
   public static Block BarleyStairs;
   public static BlockSlab BarleyHalfStep;
   public static BlockSlab BarleyStep;
   public static Block JellyOre;
   public static Block JellyJump;
   public static Block JellyJumpHard;
   public static Block JellyJumpRelative;
   public static Block LollipopBlock;
   public static Block LollipopPlant;
   public static Block CaramelBlock;
   public static Block SugarFactory;
   public static Block SugarFurnace;
   public static Block SugarFurnaceOn;
   public static BlockCandyPortal CandyPortal;
   public static Block SugarBlock;
   public static Block ChocolateStone;
   public static Block ChocolateCobbleStone;
   public static Block CandyWaterLily;
   public static Block DragiCrops;
   public static Block PinkAlgae;
   public static Block GreenAlgae;
   public static Block CandyWorkbench;
   public static Block CandyLadder;
   public static Block CandyDoor;
   public static Block CandyFlower;
   public static BlockCandyChest CandyChest;
   public static Block HoneyOre;
   public static Block HoneyTorch;
   public static Block HoneyBlock;
   public static Block HoneyLamp;
   public static Block PEZOre;
   public static Block PEZBlock;
   public static BlockCandyLiquid GrenadineFlow;
   public static BlockCandyLiquid GrenadineStatic;
   public static Block JawBreakerBlock;
   public static Block PurpleJellyJump;
   public static Block CottonCandyBlock;
   public static Block JawBreakerLight;
   public static Block CranberrySpikes;
   public static Block CottonCandyStairs;
   public static BlockSlab CottonCandyHalfStep;
   public static BlockSlab CottonCandyStep;
   public static Block CottonCandyBedBlock;
   public static Block MintBlock;
   public static Block RaspberryBlock;
   public static Block JellySentryKeyHole;
   public static Block JellyBossKeyHole;
   public static Block SugarSpike;
   public static Block BlockTeleporter;
   public static Block CaramelGlass0;
   public static Block CaramelGlass1;
   public static Block CaramelGlass2;
   public static Block CaramelPane0;
   public static Block CaramelPane1;
   public static Block CaramelPane2;
   public static Block CottonCandyWeb;
   public static Block CherryBlock;
   public static Block BananaSeaweeds;
   public static Block CandyStairs2;
   public static Block CandyStairs3;
   public static Block NougatOre;
   public static Block AdvancedSugarFactory;
   public static Block PoisonousFlower;
   public static Block NougatBlock;
   public static Block NougatHead;
   public static Block BananaBlock;
   public static Block ChewingGumBlock;
   public static Block ChewingGumPlate;
   public static Block AlchemyTable;
   public static Block MarshmallowFlowerBlock;
   public static Block GrenadineBlock;
   public static Block IceCream;
   public static Block IceCreamStairs0;
   public static Block IceCreamStairs1;
   public static Block IceCreamStairs2;
   public static Block IceCreamStairs3;
   public static BlockSlab IceCreamHalfStep0;
   public static BlockSlab IceCreamStep0;
   public static BlockSlab IceCreamHalfStep1;
   public static BlockSlab IceCreamStep1;
   public static BlockSlab IceCreamHalfStep2;
   public static BlockSlab IceCreamStep2;
   public static BlockSlab IceCreamHalfStep3;
   public static BlockSlab IceCreamStep3;
   public static Block GoldenFlower;
   public static Block DragonEgg;
   public static Block BeetleBlockEgg;
   public static Item Licorice;
   public static Item LicoriceSpear;
   public static Item LicoriceSword;
   public static Item LicoriceShovel;
   public static Item LicoricePickAxe;
   public static Item LicoriceAxe;
   public static Item LicoriceHoe;
   public static Item CandySword;
   public static Item CandyShovel;
   public static Item CandyPickAxe;
   public static Item CandyAxe;
   public static Item CandyHoe;
   public static Item BarleySugar;
   public static Item LicoriceHelmet;
   public static Item LicoricePlate;
   public static Item LicoriceLeggings;
   public static Item LicoriceBoots;
   public static Item CaramelBucket;
   public static Item LollipopSeeds;
   public static Item Lollipop;
   public static Item Dragibus;
   public static Item DragibusStick;
   public static Item CandyStick;
   public static Item CandyDoorI;
   public static Item HoneyShard;
   public static Item ChocolateCoins;
   public static Item HoneyComb;
   public static Item HoneyHelmet;
   public static Item HoneyPlate;
   public static Item HoneyLeggings;
   public static Item HoneyBoots;
   public static Item HoneySword;
   public static Item HoneyShovel;
   public static Item HoneyPickAxe;
   public static Item HoneyAxe;
   public static Item HoneyHoe;
   public static Item CaramelBow;
   public static Item HoneyArrow;
   public static Item HoneyBolt;
   public static Item Gummy;
   public static Item HotGummy;
   public static Item GummyBall;
   public static Item PEZ;
   public static Item PEZDust;
   public static Item PEZHelmet;
   public static Item PEZPlate;
   public static Item PEZLeggings;
   public static Item PEZBoots;
   public static Item PEZSword;
   public static Item PEZShovel;
   public static Item PEZPickAxe;
   public static Item PEZAxe;
   public static Item PEZHoe;
   public static Item GrenadineBucket;
   public static Item CottonCandy;
   public static Item CranberryFish;
   public static Item CranberryFishCooked;
   public static Item CranberryScale;
   public static Item CottonCandyBed;
   public static Item SugarCrystal;
   public static Item JellyCrown;
   public static Item JellyWand;
   public static Item WaterMask;
   public static Item MysteriousEgg;
   public static Item JumpWand;
   public static Item FallBoots;
   public static Item CandiedCherry;
   public static Item WaffleNugget;
   public static Item Waffle;
   public static Item NougatPowder;
   public static Item Dynamite;
   public static Item GlueDynamite;
   public static Item ChewingGum;
   public static Item BeetleEgg;
   public static Item GrenadineCandy;
   public static Item MarshmallowFlower;
   public static Item CaramelCrossbow;
   public static Item Fork;
   public static Item Wiki;
   public static Item OrangeKey;
   public static Item BlueKey;
   public static Item WhiteKey;
   public static Item PurpleKey;
   public static Item JellyKey;
   public static Item Jelly2Key;
   public static Item CD1;
   public static Item CD2;
   public static Item CD3;
   public static Item CD4;
   public static Item CandyPlacer;
   public static Item HoneyEmblem;
   public static Item JellyEmblem;
   public static Item SuguardEmblem;
   public static Item CranberryEmblem;
   public static Item GingerbreadEmblem;
   public static Item WaterEmblem;
   public static Item ChewingGumEmblem;
   public static Item SkyEmblem;
   public static int EntityCandyPigID = 1;
   public static int EntityCandyCreeperID = 2;
   public static int EntitySuguardID = 3;
   public static int EntitySuguardMageID = 4;
   public static int EntityCandyWolfID = 5;
   public static int EntityCandySlimeID = 6;
   public static int EntityBunnyID = 7;
   public static int EntityBeeID = 8;
   public static int EntityGingerBreadID = 9;
   public static int EntityCandyArrowID = 10;
   public static int EntityGummyBallID = 11;
   public static int EntitySuguardBossID = 12;
   public static int EntitySpiderID = 13;
   public static int EntityFishID = 14;
   public static int EntityNessieID = 15;
   public static int EntityWaffleSheepID = 16;
   public static int EntitySprinterSlimeID = 17;
   public static int EntityKamikazeSlimeID = 18;
   public static int EntityTornadoSlimeID = 19;
   public static int EntityPEZSlimeID = 20;
   public static int EntityKingSlimeID = 21;
   public static int EntityDynamiteID = 22;
   public static int EntityGlueDynamiteID = 23;
   public static int EntityNougatGolemID = 24;
   public static int EntityBeetleID = 25;
   public static int EntityBossBeetleID = 26;
   public static int EntityPingouinID = 27;
   public static int EntityDragonID = 28;
   public static int EntityKingBeetleID = 29;
   public static Enchantment eat;
   public static Enchantment honeyGlue;

   public static List<Item> getOwnItems() {
      return ownItems;
   }

   private static Item reg(Item item, String unlocalizedName) {
      item.setTranslationKey(unlocalizedName);
      item.setRegistryName("candycraftmod", unlocalizedName.toLowerCase());
      item.setCreativeTab(PuddingTabs);
      ownItems.add(item);
      itemList.add(item);
      return item;
   }

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      new CandyCraftConfig(event.getModConfigurationDirectory());
      this.config = new CandyConfiguration(new File(event.getModConfigurationDirectory(), "CandyCraft/CandyConfig.cfg"));
      if (event.getSide() == Side.CLIENT) {
         clientTicker = new ClientTick();
      }
      serverTicker = new ServerTick();
      MinecraftForge.EVENT_BUS.register(new EventCatcher());
      MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainCatcher());
      NetworkRegistry.INSTANCE.registerGuiHandler(this, this.guiHandler);
      CandyNetwork.init();
      candyWorldType = new WorldTypeCandy();
      this.doConfig();
      proxy.preInit();
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      CandyRecipes.registerSmelting();
      proxy.init();
   }

   public void doConfig() {
      this.config.load();
      FluidRegistry.registerFluid(fluid);
      idDimension = this.config.get("Dimension", "Id", 23).getInt();
      idDimension2 = this.config.get("Dimension", "Dungeon", 24).getInt();
      CandyBlockRegistry.init();
      this.initMaterials();
      this.initItems();
      EnumEnchantmentType devourer = EnumHelper.addEnchantmentType("Fork", item -> item == Fork);
      EnumEnchantmentType honey = EnumHelper.addEnchantmentType("Honey", item -> item == CaramelBow);
      eat = (new EnchantmentEat(Enchantment.Rarity.COMMON, devourer)).setName("Devourer");
      eat.setRegistryName("candycraftmod", "devourer");
      honeyGlue = (new EnchantmentHoney(Enchantment.Rarity.RARE, honey)).setName("HoneyGlue");
      honeyGlue.setRegistryName("candycraftmod", "honeyglue");
      CandyWorldRegistry.init();
      CandyWorldRegistry.registerDimensions();
      new AlchemyRecipes();
      this.doMiningLevel();
      this.config.save();
   }

   private void initMaterials() {
      LicoriceMaterial = EnumHelper.addToolMaterial("Licorice", 1, 175, 4.0F, 1.0F, 8);
      // 1.12 armor reduction arrays are ordered boots, leggings, chestplate, helmet
      LicoriceArmorMaterial = EnumHelper.addArmorMaterial("Licorice", "candycraftmod:licorice", 18, new int[]{1, 4, 5, 1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
      HoneyMaterial = EnumHelper.addToolMaterial("Honey", 3, 400, 7.0F, 2.0F, 18);
      HoneyArmorMaterial = EnumHelper.addArmorMaterial("Honey", "candycraftmod:honey", 22, new int[]{2, 6, 7, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
      PEZMaterial = EnumHelper.addToolMaterial("PEZ", 4, 1034, 7.6F, 3.4F, 3);
      PEZArmorMaterial = EnumHelper.addArmorMaterial("PEZ", "candycraftmod:pez", 24, new int[]{4, 7, 9, 4}, 6, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
      UselessMaterial = EnumHelper.addArmorMaterial("Useless", "candycraftmod:useless", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   }

   private void initItems() {
      Licorice = reg(new ItemFood(6, true), "I0");
      LicoriceSpear = reg(new ItemSword(LicoriceMaterial), "I6");
      LicoriceSword = reg(new ItemSword(LicoriceMaterial), "I1");
      LicoriceShovel = reg(new ItemSpade(LicoriceMaterial), "I2");
      LicoricePickAxe = reg(new ItemCandyPickaxe(LicoriceMaterial), "I3");
      LicoriceAxe = reg(new ItemCandyAxe(LicoriceMaterial), "I4");
      LicoriceHoe = reg(new ItemHoe(LicoriceMaterial), "I5");
      LicoriceHelmet = reg(new ItemDreamArmor(LicoriceArmorMaterial, 0), "I7");
      LicoricePlate = reg(new ItemDreamArmor(LicoriceArmorMaterial, 1), "I8");
      LicoriceLeggings = reg(new ItemDreamArmor(LicoriceArmorMaterial, 2), "I9");
      LicoriceBoots = reg(new ItemDreamArmor(LicoriceArmorMaterial, 3), "I10");
      BarleySugar = reg(new ItemFood(4, true), "I12");
      CaramelBucket = reg(new ItemCaramelBucket(), "I14");
      LollipopSeeds = reg(new ItemCandySeeds(), "I13");
      Lollipop = reg(new ItemLollipop(), "I15");
      Fork = reg(new ItemFork(), "I16");
      Dragibus = reg(new ItemCandySeedFood(1, 0.3F, DragiCrops), "I18");
      DragibusStick = reg(new ItemDragibusStick(), "I19");
      CandyStick = reg(new ItemBase("candycraftmod:I21"), "I21");
      CandyDoorI = reg(new ItemCandyDoor(CandyDoor), "I22");
      HoneyShard = reg(new ItemBase("candycraftmod:I23"), "I23");
      ChocolateCoins = reg(new ItemFood(2, false), "I27");
      HoneyComb = reg(new ItemBase("candycraftmod:I37"), "I37");
      HoneyHelmet = reg(new ItemDreamArmor(HoneyArmorMaterial, 0), "I28");
      HoneyPlate = reg(new ItemDreamArmor(HoneyArmorMaterial, 1), "I29");
      HoneyLeggings = reg(new ItemDreamArmor(HoneyArmorMaterial, 2), "I30");
      HoneyBoots = reg(new ItemDreamArmor(HoneyArmorMaterial, 3), "I31");
      HoneySword = reg(new ItemSword(HoneyMaterial), "I32");
      HoneyShovel = reg(new ItemSpade(HoneyMaterial), "I33");
      HoneyPickAxe = reg(new ItemCandyPickaxe(HoneyMaterial), "I34");
      HoneyAxe = reg(new ItemCandyAxe(HoneyMaterial), "I35");
      HoneyHoe = reg(new ItemHoe(HoneyMaterial), "I36");
      CaramelBow = reg(new ItemCandyBow(), "I38");
      HoneyArrow = reg(new ItemBase("candycraftmod:I42"), "I42");
      Gummy = reg(new ItemFood(4, false).setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 600, 0), 0.9F), "I44");
      HotGummy = reg(new ItemFood(7, false).setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1200, 1), 0.9F), "I45");
      GummyBall = reg(new ItemGummyBall(), "I43");
      PEZ = reg(new ItemFood(10, false).setPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0), 0.9F).setAlwaysEdible(), "I48");
      PEZDust = reg(new ItemBase("candycraftmod:I49"), "I49");
      PEZHelmet = reg(new ItemDreamArmor(PEZArmorMaterial, 0), "I50");
      PEZPlate = reg(new ItemDreamArmor(PEZArmorMaterial, 1), "I51");
      PEZLeggings = reg(new ItemDreamArmor(PEZArmorMaterial, 2), "I52");
      PEZBoots = reg(new ItemDreamArmor(PEZArmorMaterial, 3), "I53");
      PEZSword = reg(new ItemSword(PEZMaterial), "I54");
      PEZShovel = reg(new ItemSpade(PEZMaterial), "I55");
      PEZPickAxe = reg(new ItemCandyPickaxe(PEZMaterial), "I56");
      PEZAxe = reg(new ItemCandyAxe(PEZMaterial), "I57");
      PEZHoe = reg(new ItemHoe(PEZMaterial), "I58");
      GrenadineBucket = reg(new ItemBucket(GrenadineFlow).setContainerItem(Items.BUCKET), "I59");
      CottonCandy = reg(new ItemFood(3, true).setPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 0), 0.9F), "I60");
      CranberryFish = reg(new ItemFood(2, true).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 600, 0), 0.9F), "I64");
      CranberryFishCooked = reg(new ItemFood(6, true).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 1200, 0), 0.9F), "I65");
      CranberryScale = reg(new ItemBase("candycraftmod:I63"), "I63");
      CottonCandyBed = reg(new ItemCandyBed().setMaxStackSize(1), "I66");
      SugarCrystal = reg(new Item(), "I69");
      JellyCrown = reg(new ItemDreamArmor(UselessMaterial, 0), "I70");
      JellyWand = reg(new ItemJellyWand(), "I71");
      WaterMask = reg(new ItemDreamArmor(UselessMaterial, 0), "I72");
      MysteriousEgg = reg(new ItemEggPlacer("candycraftmod:I73", DragonEgg), "I73");
      JumpWand = reg(new ItemJumpWand(), "I74");
      FallBoots = reg(new ItemDreamArmor(UselessMaterial, 3), "I75");
      CandiedCherry = reg(new ItemFood(3, true), "I76");
      WaffleNugget = reg(new ItemFood(1, true), "I77");
      Waffle = reg(new ItemFood(10, true), "I78");
      CandySword = reg(new ItemSword(ToolMaterial.WOOD), "I79");
      CandyShovel = reg(new ItemSpade(ToolMaterial.WOOD), "I81");
      CandyPickAxe = reg(new ItemCandyPickaxe(ToolMaterial.WOOD), "I82");
      CandyAxe = reg(new ItemCandyAxe(ToolMaterial.WOOD), "I80");
      CandyHoe = reg(new ItemHoe(ToolMaterial.WOOD), "I83");
      NougatPowder = reg(new ItemNougatPowder(3, true), "I84");
      Dynamite = reg(new ItemDynamite(), "I85");
      GlueDynamite = reg(new ItemDynamite(), "I89");
      ChewingGum = reg(new ItemBase("candycraftmod:I91"), "I91");
      BeetleEgg = reg(new ItemEggPlacer("candycraftmod:I92", BeetleBlockEgg), "I92");
      GrenadineCandy = reg(new ItemGrenadineCandy().setAlwaysEdible(), "I94");
      GrenadineCandy.setCreativeTab(null);
      MarshmallowFlower = reg(new ItemBase("candycraftmod:I95"), "I95");
      HoneyBolt = reg(new ItemBase("candycraftmod:I100"), "I100");
      CaramelCrossbow = reg(new ItemCandyCrossbow(), "I99");
      CD1 = reg(new ItemCandyRecord("CD-1", CandyStepSound.getOrCreate("records.CD-1"), "C418 - Sweden - Remix Caution & Crisis", "Sweden Remix", "I11"), "I11");
      CD2 = reg(new ItemCandyRecord("CD-2", CandyStepSound.getOrCreate("records.CD-2"), "Jakim - Every", "Every", "I46"), "I46");
      CD3 = reg(new ItemCandyRecord("CD-3", CandyStepSound.getOrCreate("records.CD-3"), "Rainbow Bunchie", "Rainbow Bunchie", "I98"), "I98");
      CD4 = reg(new ItemCandyRecord("CD-4", CandyStepSound.getOrCreate("records.CD-4"), "C418 - Einfallslos", "Einfallslos", "I101"), "I101");
      CandyPlacer = new ItemCandyMonsterPlacer();
      Wiki = reg(new ItemWiki(), "I17");
      OrangeKey = reg(new ItemKey("candycraftmod:I20"), "I20");
      BlueKey = reg(new ItemKey("candycraftmod:I47"), "I47");
      WhiteKey = reg(new ItemKey("candycraftmod:I96"), "I96");
      PurpleKey = reg(new ItemKey("candycraftmod:I102"), "I102");
      HoneyEmblem = reg(new ItemEmblem("HoneyEmblem"), "I24");
      JellyEmblem = reg(new ItemEmblem("JellyEmblem"), "I25");
      SuguardEmblem = reg(new ItemEmblem("SuguardEmblem"), "I26");
      CranberryEmblem = reg(new ItemEmblem("CranberryEmblem"), "I61");
      GingerbreadEmblem = reg(new ItemEmblem("GingerbreadEmblem"), "I62");
      WaterEmblem = reg(new ItemEmblem("WaterEmblem"), "I88");
      ChewingGumEmblem = reg(new ItemEmblem("ChewingGumEmblem"), "I93");
      SkyEmblem = reg(new ItemEmblem("SkyEmblem"), "I97");
      JellyKey = reg(new ItemKey("candycraftmod:I67"), "I67");
      Jelly2Key = reg(new ItemKey("candycraftmod:I68"), "I68");
      LicoriceMaterial.setRepairItem(new ItemStack(Licorice));
      LicoriceArmorMaterial.setRepairItem(new ItemStack(Licorice));
      HoneyMaterial.setRepairItem(new ItemStack(HoneyComb));
      HoneyArmorMaterial.setRepairItem(new ItemStack(HoneyComb));
      PEZMaterial.setRepairItem(new ItemStack(PEZ));
      PEZArmorMaterial.setRepairItem(new ItemStack(PEZ));
   }

   private void doMiningLevel() {
      LicoriceOre.setHarvestLevel("pickaxe", 1);
      PEZOre.setHarvestLevel("pickaxe", 3);
      HoneyOre.setHarvestLevel("pickaxe", 0);
      ChocolateStone.setHarvestLevel("pickaxe", 0);
      ChocolateCobbleStone.setHarvestLevel("pickaxe", 0);
      SugarFurnace.setHarvestLevel("pickaxe", 0);
      JellyOre.setHarvestLevel("pickaxe", 2);
      NougatOre.setHarvestLevel("pickaxe", 2);
      PuddingBlock.setHarvestLevel("shovel", 0);
      FlourBlock.setHarvestLevel("shovel", 0);
   }

   @EventHandler
   public void serverLoad(FMLServerStartingEvent event) {
      event.registerServerCommand(new WikiCommand());
   }

   @SubscribeEvent
   public static void registerBlocks(RegistryEvent.Register<Block> event) {
      CandyBlockRegistry.registerBlocks(event);
   }

   @SubscribeEvent
   public static void registerItems(RegistryEvent.Register<Item> event) {
      for (Item item : ownItems) {
         event.getRegistry().register(item);
      }
      CandyBlockRegistry.registerItemBlocks(event);
      CandyBlockRegistry.registerTileEntities();
   }

   @SubscribeEvent
   public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
      CandyRecipes.register();
   }

   @SubscribeEvent
   public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
      event.getRegistry().register(eat);
      event.getRegistry().register(honeyGlue);
   }

   @SubscribeEvent
   public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
      CandySounds.register(event);
      CandyStepSound.registerAll(event.getRegistry());
   }

   @SubscribeEvent
   public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
      CandyEntityRegistry.register(event);
   }

   @SubscribeEvent
   public static void registerBiomes(RegistryEvent.Register<Biome> event) {
      CandyWorldRegistry.registerBiomes(event);
   }
}
