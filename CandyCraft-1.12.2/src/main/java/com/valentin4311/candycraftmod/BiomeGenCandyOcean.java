package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyOcean extends BiomeGenCandy {
   public BiomeGenCandyOcean(String par2) {
      super(props(par2, -1.0F, 0.1F, 16737877));
      this.spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntityNessie.class, 1, 1, 3));
      this.theBiomeDecorator2.waterlilyPerChunk = 0;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public int getGrassColorAtPos(BlockPos pos) {
      return 11755263;
   }
   @Override
   @SideOnly(Side.CLIENT)
   public int getSkyColorByTemp(float par1) {
      return new Color(205, 149, 255).getRGB();
   }
}
