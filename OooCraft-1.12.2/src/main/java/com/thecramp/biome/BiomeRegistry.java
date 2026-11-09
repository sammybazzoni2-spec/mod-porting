package com.thecramp.biome;

import com.thecramp.lib.RefStrings;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeRegistry {
   public static Biome biomeDimension1;
   public static Biome biomeLemon;

   public static void MainClass(IForgeRegistry<Biome> registry) {
      initializeBiome();
      registerBiome(registry);
   }

   public static void initializeBiome() {
      biomeDimension1 = new BiomeGenDimension1(new Biome.BiomeProperties("Dimension1Biome").setTemperature(1.2F).setRainfall(0.9F));
      biomeDimension1.setRegistryName(RefStrings.MODID, "dimension1_biome");
      biomeLemon = new BiomeGenLemon(new Biome.BiomeProperties("LemonBiome"));
      biomeLemon.setRegistryName(RefStrings.MODID, "lemon_biome");
   }

   public static void registerBiome(IForgeRegistry<Biome> registry) {
      registry.register(biomeDimension1);
      registry.register(biomeLemon);
      BiomeDictionary.addTypes(biomeDimension1, Type.DRY, Type.SANDY);
      BiomeManager.addSpawnBiome(biomeDimension1);
      BiomeDictionary.addTypes(biomeLemon, Type.DRY, Type.SANDY);
   }
}
