package com.valentin4311.candycraftmod;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderVoid extends WorldProvider {
   @Override
   protected void init() {
      this.biomeProvider = new BiomeProviderSingle(CandyCraft.CandyVoid);
      this.hasSkyLight = false;
   }

   @Override
   public DimensionType getDimensionType() {
      return CandyWorldRegistry.dungeonDimension;
   }

   @Override
   public boolean doesWaterVaporize() {
      return true;
   }

   @Override
   public IChunkGenerator createChunkGenerator() {
      return new ChunkProviderCandyVoid(this.world, this.world.getSeed(), false);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public Vec3d getFogColor(float par1, float par2) {
      float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
      if (f2 < 0.0F) {
         f2 = 0.0F;
      }

      if (f2 > 1.0F) {
         f2 = 1.0F;
      }

      float f3 = 0.7529412F;
      float f4 = 0.5705883F;
      float f5 = 1.0F;
      f3 *= f2 * 0.94F + 0.06F;
      f4 *= f2 * 0.94F + 0.06F;
      f5 *= f2 * 0.91F + 0.09F;
      return new Vec3d((double)f3, (double)f4, (double)f5);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isSkyColored() {
      return true;
   }

   public String getWelcomeMessage() {
      return "Entering the Dungeon";
   }

   public String getDepartMessage() {
      return "Leaving the Dungeon";
   }

   @Override
   public boolean canRespawnHere() {
      return false;
   }

   @Override
   public boolean isSurfaceWorld() {
      return false;
   }
}
