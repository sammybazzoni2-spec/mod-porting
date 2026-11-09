package com.thecramp.Main;

import com.thecramp.armor.FinnHat;
import com.thecramp.armor.IceCrown;
import com.thecramp.biome.BiomeRegistry;
import com.thecramp.biome.WorldTypeBiomeDimension1;
import com.thecramp.block.GrassBunch;
import com.thecramp.block.HardGrass;
import com.thecramp.block.LemonDirt;
import com.thecramp.block.LemonGrass;
import com.thecramp.block.LumpyDirt;
import com.thecramp.block.LumpyGrass;
import com.thecramp.dimension.Dimension1Portal;
import com.thecramp.dimension.DimensionRegistry;
import com.thecramp.entity.EntityDarren;
import com.thecramp.entity.EntityDemon;
import com.thecramp.entity.EntityFinnParadox;
import com.thecramp.entity.EntityGunter;
import com.thecramp.entity.EntityJamesBaxter;
import com.thecramp.entity.EntityLemongrab;
import com.thecramp.entity.EntityLich;
import com.thecramp.entity.EntityMagicMan;
import com.thecramp.item.BaconPancakes;
import com.thecramp.item.BrewingSpell;
import com.thecramp.item.DemonBlood;
import com.thecramp.item.DemonEye;
import com.thecramp.item.DemonIngot;
import com.thecramp.item.DemonPickaxe;
import com.thecramp.item.DemonSword;
import com.thecramp.item.Enchiridion;
import com.thecramp.item.FinnSword;
import com.thecramp.item.Genius;
import com.thecramp.item.GrassSwordSword;
import com.thecramp.item.HoloPlayer;
import com.thecramp.item.ItemLemon;
import com.thecramp.item.MarcyAxe;
import com.thecramp.item.Meatloaf;
import com.thecramp.item.Pancake;
import com.thecramp.item.ScarletSword;
import com.thecramp.item.Spoon;
import com.thecramp.item.UltimateSerum;
import com.thecramp.lib.ModSounds;
import com.thecramp.lib.RefStrings;
import com.thecramp.tab.CreativeTabsBoss;
import com.thecramp.tab.CreativeTabsOooCrafting;
import com.thecramp.tab.CreativeTabsOooFood;
import com.thecramp.tab.CreativeTabsOooWeapons;
import com.thecramp.tab.CreatuveTabsOooBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
   modid = "ooocraft",
   name = "OooCraft",
   version = "0.2",
   acceptedMinecraftVersions = "[1.12.2]"
)
@Mod.EventBusSubscriber(modid = "ooocraft")
public class MainRegistry {
   @Instance("ooocraft")
   public static MainRegistry instance;
   public static final List<Item> ALL_ITEMS = new ArrayList<Item>();
   public static Item backpack;
   public static Item demonSword;
   public static Item scarletSword;
   public static Item grassSword;
   public static Item demonPick;
   public static Item HoloPlayer;
   public static Item bubblegum;
   public static Item bubblegumSerum;
   public static Item chocolateSerum;
   public static Item peppermintSerum;
   public static Item bananaSerum;
   public static Item strawberrySerum;
   public static Item toffeeSerum;
   public static Item ultimateSerum;
   public static Item DemonEye;
   public static Item IceCrown;
   public static Item FinnHat;
   public static Item Sweater;
   public static Item iceGem;
   public static Item heroFabric;
   public static Item spoon;
   public static Item finnSword;
   public static Item marcyAxe;
   public static Item brewingSpell;
   public static Item vampireBlood;
   public static Item genius;
   public static Item corruptedSpell;
   public static Item shinyNetherWart;
   public static Item lemon;
   public static Item lichShard;
   public static Item enchiridion;
   public static Item lemonShard;
   public static Item meatloaf;
   public static Item pancake;
   public static Item baconPancake;
   public static Block grassBunch;
   public static Block hardGrass;
   public static Block dimension1Portal;
   public static Block lumpyGrass;
   public static Block lumpyDirt;
   public static Block dungeon1;
   public static Block lemonGrass;
   public static Block lemonDirt;
   public static CreativeTabs tabBoss = new CreativeTabsBoss("Boss");
   public static CreativeTabs tabBlock = new CreatuveTabsOooBlock("OooBlock");
   public static CreativeTabs tabWeapons = new CreativeTabsOooWeapons("OooWeapons");
   public static CreativeTabs tabCrafting = new CreativeTabsOooCrafting("OooCrafting");
   public static CreativeTabs tabFood = new CreativeTabsOooFood("OooFood");
   public static final Item.ToolMaterial DEMON = EnumHelper.addToolMaterial("DEMON", 3, 2500, 15.0F, 6.0F, 12);
   public static final Item.ToolMaterial GRASS = EnumHelper.addToolMaterial("GRASS", 3, 2500, 15.0F, 7.0F, 12);
   public static final Item.ToolMaterial SCARLET = EnumHelper.addToolMaterial("SCARLET", 3, 2500, 15.0F, 5.0F, 12);
   public static Item.ToolMaterial enumToolMaterialIce = EnumHelper.addToolMaterial("ICE", 3, 10000000, 9.0F, 4.0F, 0);
   // 1.12 armor reduction array order is {boots, leggings, chestplate, helmet}
   public static ItemArmor.ArmorMaterial enumArmorMaterialIce = EnumHelper.addArmorMaterial("ICE", "ooocraft:ice", 10000000, new int[]{1, 1, 1, 10}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   public static Item.ToolMaterial enumToolMaterialFinn = EnumHelper.addToolMaterial("FINN", 3, 2500, 9.0F, 7.5F, 0);
   public static ItemArmor.ArmorMaterial enumArmorMaterialFinn = EnumHelper.addArmorMaterial("FINN", "ooocraft:finn", 2500, new int[]{1, 1, 10, 5}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   @SidedProxy(
      clientSide = "com.thecramp.Main.ClientProxy",
      serverSide = "com.thecramp.Main.ServerProxy"
   )
   public static ServerProxy proxy;

   private static Item name(Item item, String unlocalized, String registryName, CreativeTabs tab) {
      item.setUnlocalizedName(unlocalized);
      if (tab != null) {
         item.setCreativeTab(tab);
      }
      item.setRegistryName(RefStrings.MODID, registryName);
      return item;
   }

   private static Block name(Block block, String unlocalized, String registryName, CreativeTabs tab) {
      block.setUnlocalizedName(unlocalized);
      block.setCreativeTab(tab);
      block.setRegistryName(RefStrings.MODID, registryName);
      return block;
   }

   private static void registerEntity(List<EntityEntry> list, Class<? extends Entity> entityClass, String name, int id, EnumCreatureType type, int weight, int min, int max, Biome biome) {
      long seed = (long)name.hashCode();
      Random rand = new Random(seed);
      int primaryColor = (rand.nextInt() * 16777215) & 16777215;
      int secondaryColor = (rand.nextInt() * 16777215) & 16777215;
      EntityEntryBuilder<Entity> builder = EntityEntryBuilder.create()
         .entity(entityClass)
         .id(new ResourceLocation(RefStrings.MODID, name.toLowerCase()), id)
         .name(name)
         .tracker(64, 1, true)
         .egg(primaryColor, secondaryColor);
      if (biome != null) {
         builder.spawn(type, weight, min, max, biome);
      }
      list.add(builder.build());
   }

   @SubscribeEvent
   public static void registerBlocks(RegistryEvent.Register<Block> event) {
      grassBunch = name((new GrassBunch()).setHardness(2.0F), "grassBunch", "grass_bunch", tabBlock);
      hardGrass = name((new HardGrass()).setHardness(5.0F), "hardGrass", "hard_grass", tabBlock);
      lemonGrass = name((new LemonGrass()).setHardness(1.5F), "lemonGrass", "lemon_grass", tabBlock);
      lemonDirt = name((new LemonDirt()).setHardness(1.5F), "lemonDirt", "lemon_dirt", tabBlock);
      lumpyGrass = name((new LumpyGrass()).setHardness(1.5F), "lumpyGrass", "lumpy_grass", tabBlock);
      lumpyDirt = name((new LumpyDirt()).setHardness(1.5F), "lumpyDirt", "lumpy_dirt", tabBlock);
      dimension1Portal = name(new Dimension1Portal(), "dimension1Portal", "dimension1_portal", tabBlock);
      event.getRegistry().registerAll(grassBunch, hardGrass, lemonGrass, lemonDirt, lumpyGrass, lumpyDirt, dimension1Portal);
   }

   @SubscribeEvent
   public static void registerItems(RegistryEvent.Register<Item> event) {
      ALL_ITEMS.clear();
      DemonBlood.mainRegistry();
      DemonIngot.mainRegistry();
      DemonBlood.dBlood.setRegistryName(RefStrings.MODID, "demon_blood");
      DemonIngot.dIngot.setRegistryName(RefStrings.MODID, "demon_ingot");
      demonSword = name(new DemonSword(DEMON), "demonSword", "demon_sword", tabWeapons);
      grassSword = name(new GrassSwordSword(GRASS), "grassSword", "grass_sword", tabWeapons);
      scarletSword = name(new ScarletSword(SCARLET), "scarletSword", "scarlet_sword", tabWeapons);
      demonPick = name(new DemonPickaxe(DEMON), "demonPick", "demon_pick", tabWeapons);
      HoloPlayer = name(new HoloPlayer(), "HoloPlayer", "holo_player", tabWeapons);
      DemonEye = name(new DemonEye(), "DemonEye", "demon_eye", tabWeapons);
      bubblegum = name(new Item(), "bubblegum", "bubblegum", tabCrafting);
      bubblegumSerum = name(new Item(), "bubblegumSerum", "bubblegum_serum", tabCrafting);
      toffeeSerum = name(new Item(), "toffeeSerum", "toffee_serum", tabCrafting);
      chocolateSerum = name(new Item(), "chocolateSerum", "chocolate_serum", tabCrafting);
      peppermintSerum = name(new Item(), "peppermintSerum", "peppermint_serum", tabCrafting);
      bananaSerum = name(new Item(), "bananaSerum", "banana_serum", tabCrafting);
      strawberrySerum = name(new Item(), "strawberrySerum", "strawberry_serum", tabCrafting);
      ultimateSerum = name(new UltimateSerum(), "ultimateSerum", "ultimate_serum", tabWeapons);
      IceCrown = name(new IceCrown(enumArmorMaterialIce, 5, EntityEquipmentSlot.HEAD), "iceCrown", "ice_crown", tabWeapons);
      iceGem = name(new Item(), "iceGem", "ice_gem", tabCrafting);
      FinnHat = name(new FinnHat(enumArmorMaterialFinn, 6, EntityEquipmentSlot.HEAD), "finnHat", "finn_hat", tabWeapons);
      Sweater = name(new FinnHat(enumArmorMaterialFinn, 6, EntityEquipmentSlot.CHEST), "sweater", "sweater", tabWeapons);
      heroFabric = name(new Item(), "heroFabric", "hero_fabric", tabCrafting);
      spoon = name(new Spoon(), "spoon", "spoon", tabWeapons);
      marcyAxe = name(new MarcyAxe(SCARLET), "marcyAxe", "marcy_axe", tabWeapons);
      finnSword = name(new FinnSword(enumToolMaterialFinn), "finnSword", "finn_sword", tabWeapons);
      brewingSpell = name(new Item(), "brewingSpell", "brewing_spell", tabCrafting);
      vampireBlood = name(new Item(), "vBlood", "vampire_blood", tabCrafting);
      genius = name(new Genius(), "genius", "genius", tabWeapons);
      corruptedSpell = name(new BrewingSpell(), "corruptedSpell", "corrupted_spell", tabCrafting);
      shinyNetherWart = name(new Item(), "shinyNetherWart", "shiny_netherwart", tabCrafting);
      lichShard = name(new Item(), "lichShard", "lich_shard", tabCrafting);
      enchiridion = name(new Enchiridion(), "enchiridion", "enchiridion", tabBoss);
      lemon = name(new ItemLemon(), "lemon", "lemon", tabBoss);
      lemonShard = name(new Item(), "lemonShard", "lemon_shard", tabCrafting);
      meatloaf = name(new Meatloaf(20, 3.0F, false), "meatloaf", "meatloaf", null);
      pancake = name(new Pancake(6, 1.0F, false), "pancake", "pancake", null);
      baconPancake = name(new BaconPancakes(20, 1.0F, false), "baconPancake", "bacon_pancake", null);

      Item[] items = new Item[]{
         DemonBlood.dBlood, DemonIngot.dIngot, demonSword, grassSword, scarletSword, demonPick, HoloPlayer, DemonEye,
         bubblegum, bubblegumSerum, toffeeSerum, chocolateSerum, peppermintSerum, bananaSerum, strawberrySerum, ultimateSerum,
         IceCrown, iceGem, FinnHat, Sweater, heroFabric, spoon, marcyAxe, finnSword, brewingSpell, vampireBlood, genius,
         corruptedSpell, shinyNetherWart, lichShard, enchiridion, lemon, lemonShard, meatloaf, pancake, baconPancake
      };
      for (Item item : items) {
         event.getRegistry().register(item);
         ALL_ITEMS.add(item);
      }
      // The portal block has no item form (it was never obtainable as an item in the original).
      Block[] blocks = new Block[]{grassBunch, hardGrass, lemonGrass, lemonDirt, lumpyGrass, lumpyDirt};
      for (Block block : blocks) {
         Item itemBlock = new ItemBlock(block).setRegistryName(block.getRegistryName());
         itemBlock.setCreativeTab(block.getCreativeTabToDisplayOn());
         event.getRegistry().register(itemBlock);
         ALL_ITEMS.add(itemBlock);
      }
   }

   @SubscribeEvent
   public static void registerBiomes(RegistryEvent.Register<Biome> event) {
      BiomeRegistry.MainClass(event.getRegistry());
   }

   @SubscribeEvent
   public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
      ModSounds.HOLO_MESSAGE = ModSounds.create("holo_message");
      ModSounds.ULTIMATE_SERUM = ModSounds.create("ultimate_serum");
      ModSounds.GENIUS = ModSounds.create("genius");
      event.getRegistry().registerAll(ModSounds.HOLO_MESSAGE, ModSounds.ULTIMATE_SERUM, ModSounds.GENIUS);
   }

   @SubscribeEvent
   public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
      List<EntityEntry> list = new ArrayList<EntityEntry>();
      registerEntity(list, EntityDemon.class, "entityDemon", 0, EnumCreatureType.MONSTER, 10, 1, 10, Biomes.HELL);
      registerEntity(list, EntityGunter.class, "entityGunter", 1, EnumCreatureType.MONSTER, 10, 1, 10, Biomes.ICE_MOUNTAINS);
      registerEntity(list, EntityMagicMan.class, "entityMagicMan", 2, EnumCreatureType.MONSTER, 10, 1, 10, Biomes.DESERT);
      registerEntity(list, EntityFinnParadox.class, "entityFinnParadox", 3, EnumCreatureType.MONSTER, 10, 1, 10, Biomes.SKY);
      registerEntity(list, EntityJamesBaxter.class, "entityJamesBaxter", 4, EnumCreatureType.CREATURE, 10, 2, 4, Biomes.PLAINS);
      registerEntity(list, EntityLich.class, "entityLich", 5, EnumCreatureType.MONSTER, 1, 1, 1, Biomes.SWAMPLAND);
      registerEntity(list, EntityLemongrab.class, "entityLemongrab", 6, EnumCreatureType.MONSTER, 0, 0, 0, null);
      registerEntity(list, EntityDarren.class, "entityDarren", 7, EnumCreatureType.MONSTER, 10, 1, 10, Biomes.PLAINS);
      event.getRegistry().registerAll(list.toArray(new EntityEntry[0]));
   }

   @EventHandler
   public void PreLoad(FMLPreInitializationEvent PreEvent) {
      proxy.registerRenderInfo();
      proxy.registerRenderers();
      DimensionRegistry.mainRegistry();
   }

   @EventHandler
   public void load(FMLInitializationEvent event) {
      GameRegistry.addSmelting(DemonBlood.dBlood, new ItemStack(DemonIngot.dIngot), 0.8F);
      GameRegistry.addSmelting(brewingSpell, new ItemStack(corruptedSpell), 1.0F);
   }

   @EventHandler
   public void PostLoad(FMLPostInitializationEvent PostEvent) {
      new WorldTypeBiomeDimension1(3, "biomeDimension1");
   }
}
