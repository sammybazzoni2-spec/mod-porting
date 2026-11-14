package com.valentin4311.candycraftmod;

import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCandyRiver extends BiomeGenCandy {
   public BiomeGenCandyRiver(String par2) {
      super(props(par2, -0.5F, 0.0F, 16777062));
      this.spawnableCreatureList.clear();
   }
}
