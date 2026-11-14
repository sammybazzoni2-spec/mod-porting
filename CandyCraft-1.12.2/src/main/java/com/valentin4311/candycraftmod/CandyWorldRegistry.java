package com.valentin4311.candycraftmod;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;

/** Biome, biome dictionary and dimension registration for CandyCraft. */
public class CandyWorldRegistry {
   public static DimensionType candyDimension;
   public static DimensionType dungeonDimension;

   /** Creates the biome objects (called from preInit); they are registered later in {@link #registerBiomes}. */
   public static void init() {
      CandyCraft.CandyMountains = new BiomeGenMountains("SugarMountains");
      CandyCraft.CandyPlains = new BiomeGenCandyPlains("SugarPlains");
      CandyCraft.CandyForest = new BiomeGenCandyForest("SugarForest");
      CandyCraft.CandyColdForest = new BiomeGenColdForest("IceCreamForest");
      CandyCraft.CandyRiver = new BiomeGenCandyRiver("SugarRiver");
      CandyCraft.CandyHellMountains = new BiomeGenHellMountains("IceCreamSkyMountains");
      CandyCraft.CandyOcean = new BiomeGenCandyOcean("SugarOceans");
      CandyCraft.CandyEnchantedForest = new BiomeGenCandyEnchantedWoods("SugarEnchantedForest");
      CandyCraft.CandyHellForest = new BiomeGenHellForest("CaramelForest");
      CandyCraft.CandyFrostPlains = new BiomeGenFrostPlain("IceCreamPlains");
      CandyCraft.CandyVoid = new BiomeGenVoid("CandyCraft Dungeon");
   }

   private static void reg(RegistryEvent.Register<Biome> event, Biome biome, String name, BiomeDictionary.Type... types) {
      biome.setRegistryName("candycraftmod", name);
      event.getRegistry().register(biome);
      BiomeDictionary.addTypes(biome, types);
   }

   public static void registerBiomes(RegistryEvent.Register<Biome> event) {
      reg(event, CandyCraft.CandyMountains, "sugar_mountains", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyPlains, "sugar_plains", BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyForest, "sugar_forest", BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyColdForest, "ice_cream_forest", BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyRiver, "sugar_river", BiomeDictionary.Type.RIVER, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyHellMountains, "ice_cream_sky_mountains", BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.COLD, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyOcean, "sugar_oceans", BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyEnchantedForest, "sugar_enchanted_forest", BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyHellForest, "caramel_forest", BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyFrostPlains, "ice_cream_plains", BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.MAGICAL);
      reg(event, CandyCraft.CandyVoid, "dungeon", BiomeDictionary.Type.VOID, BiomeDictionary.Type.MAGICAL);
   }

   public static void registerDimensions() {
      candyDimension = DimensionType.register("CandyCraft", "_candycraft", CandyCraft.idDimension, WorldProviderCandy.class, false);
      DimensionManager.registerDimension(CandyCraft.idDimension, candyDimension);
      dungeonDimension = DimensionType.register("CandyCraft Dungeon", "_candycraft_dungeon", CandyCraft.idDimension2, WorldProviderVoid.class, false);
      DimensionManager.registerDimension(CandyCraft.idDimension2, dungeonDimension);
   }
}
