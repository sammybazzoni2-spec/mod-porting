package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderCandy extends WorldProvider {
   public static int canGenIsland = 0;
   public static int canGenVillage = 0;
   public static int canGenChewingGum = 0;
   public static int canGenGeyser = 0;
   public static int canGenHouses = 0;
   public static int canGenTemple = 0;
   public static int canGenIceTower = 0;
   @SideOnly(Side.CLIENT)
   private IRenderHandler candyWeatherRenderer;
   private static int skyX;
   private static int skyZ;
   private static boolean skyInit;
   private static int skyRGBMultiplier;

   @Override
   protected void init() {
      // The WorldInfo carries the Candy world type so that the Forge InitBiomeGens event (TerrainCatcher) swaps in the Candy GenLayers.
      WorldInfo info = new WorldInfo(new WorldSettings(this.world.getSeed(), GameType.SURVIVAL, false, false, CandyCraft.candyWorldType), "CandyCraft");
      this.biomeProvider = new BiomeProvider(info);
      this.hasSkyLight = true;
   }

   @Override
   public DimensionType getDimensionType() {
      return CandyWorldRegistry.candyDimension;
   }

   @Override
   public boolean doesWaterVaporize() {
      return true;
   }

   @Override
   public IChunkGenerator createChunkGenerator() {
      return new ChunkProviderCandyWorld(this.world, this.world.getSeed(), false);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public IRenderHandler getWeatherRenderer() {
      if (this.candyWeatherRenderer == null) {
         this.candyWeatherRenderer = new RenderWeather();
      }

      return this.candyWeatherRenderer;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public Vec3d getCloudColor(float par1) {
      return this.blendedColor(par1);
   }

   @SideOnly(Side.CLIENT)
   private Vec3d blendedColor(float partialTicks) {
      float f1 = this.world.getCelestialAngle(partialTicks);
      float f2 = MathHelper.cos(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
      if (f2 < 0.0F) {
         f2 = 0.0F;
      }

      if (f2 > 1.0F) {
         f2 = 1.0F;
      }

      EntityPlayer player = Minecraft.getMinecraft().player;
      int l = getFogBlendColour(this.world, MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ));
      float f3 = (float)(l >> 16 & 255) / 255.0F * f2;
      float f4 = (float)(l >> 8 & 255) / 255.0F * f2;
      float f5 = (float)(l & 255) / 255.0F * f2;
      return new Vec3d((double)f3, (double)f4, (double)f5);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public Vec3d getFogColor(float par1, float par2) {
      return this.blendedColor(par2);
   }

   @SideOnly(Side.CLIENT)
   public static int getFogBlendColour(World world, int playerX, int playerY, int playerZ) {
      if (playerX == skyX && playerZ == skyZ && skyInit) {
         return skyRGBMultiplier;
      } else {
         skyInit = true;
         GameSettings settings = Minecraft.getMinecraft().gameSettings;
         int[] ranges = ForgeModContainer.blendRanges;
         int distance = 0;
         if (settings.fancyGraphics && settings.renderDistanceChunks >= 0 && settings.renderDistanceChunks < ranges.length) {
            distance = ranges[settings.renderDistanceChunks];
         }

         int r = 0;
         int g = 0;
         int b = 0;
         int divider = 0;

         for(int x = -distance; x <= distance; ++x) {
            for(int z = -distance; z <= distance; ++z) {
               BlockPos p = new BlockPos(playerX + x, playerY, playerZ + z);
               Biome biome = world.getBiome(p);
               int colour = biome.getGrassColorAtPos(p);
               r += (colour & 16711680) >> 16;
               g += (colour & '＀') >> 8;
               b += colour & 255;
               ++divider;
            }
         }

         int multiplier = (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;
         skyX = playerX;
         skyZ = playerZ;
         skyRGBMultiplier = multiplier;
         return skyRGBMultiplier;
      }
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isSkyColored() {
      return true;
   }

   public String getWelcomeMessage() {
      return "Entering the Candy Valley";
   }

   public String getDepartMessage() {
      return "Leaving the Candy Valley";
   }

   @Override
   public boolean canRespawnHere() {
      return true;
   }

   @Override
   public boolean isSurfaceWorld() {
      return true;
   }
}
