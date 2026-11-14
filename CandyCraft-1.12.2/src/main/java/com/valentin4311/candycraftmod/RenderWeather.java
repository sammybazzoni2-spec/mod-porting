package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderWeather extends IRenderHandler {
   private static final ResourceLocation locationRainPng = new ResourceLocation("candycraftmod:textures/environment/rain.png");
   float[] rainXCoords;
   float[] rainYCoords;
   private int rendererUpdateCount;
   private Random random = new Random();

   @Override
   public void render(float partialTicks, WorldClient world, Minecraft mc) {
      ++this.rendererUpdateCount;
      if (ClientTick.weatherController == null || !(ClientTick.weatherController.timeLeft > 0.0D)) {
         return;
      }
      mc.entityRenderer.enableLightmap();
      if (this.rainXCoords == null) {
         this.rainXCoords = new float[1024];
         this.rainYCoords = new float[1024];
         for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
               float f2 = (float)(j - 16);
               float f3 = (float)(i - 16);
               float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
               this.rainXCoords[i << 5 | j] = -f3 / f4;
               this.rainYCoords[i << 5 | j] = f2 / f4;
            }
         }
      }
      Entity view = mc.getRenderViewEntity();
      if (view == null) {
         mc.entityRenderer.disableLightmap();
         return;
      }
      int k2 = MathHelper.floor(view.posX);
      int l2 = MathHelper.floor(view.posY);
      int i3 = MathHelper.floor(view.posZ);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      GlStateManager.disableCull();
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      GlStateManager.alphaFunc(516, 0.1F);
      double d0 = view.lastTickPosX + (view.posX - view.lastTickPosX) * (double)partialTicks;
      double d1 = view.lastTickPosY + (view.posY - view.lastTickPosY) * (double)partialTicks;
      double d2 = view.lastTickPosZ + (view.posZ - view.lastTickPosZ) * (double)partialTicks;
      int k = MathHelper.floor(d1);
      int range = mc.gameSettings.fancyGraphics ? 15 : 5;
      boolean drawing = false;
      float f5 = (float)this.rendererUpdateCount + partialTicks;
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      EntityPlayer player = mc.player;
      int l0 = WorldProviderCandy.getFogBlendColour(world, MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ));
      int cr = l0 >> 16 & 255;
      int cg = l0 >> 8 & 255;
      int cb = l0 & 255;
      double tl = ClientTick.weatherController.timeLeft;
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
      for (int l = i3 - range; l <= i3 + range; ++l) {
         for (int i1 = k2 - range; i1 <= k2 + range; ++i1) {
            int j1 = (l - i3 + 16) * 32 + i1 - k2 + 16;
            float f6 = this.rainXCoords[j1] * 0.5F;
            float f7 = this.rainYCoords[j1] * 0.5F;
            pos.setPos(i1, 0, l);
            Biome biome = world.getBiome(pos);
            int k1 = world.getPrecipitationHeight(pos).getY();
            int l1 = l2 - range;
            int i2 = l2 + range;
            if (l1 < k1) {
               l1 = k1;
            }
            if (i2 < k1) {
               i2 = k1;
            }
            int j2 = k1 < k ? k : k1;
            if (l1 == i2) {
               continue;
            }
            this.random.setSeed((long)(i1 * i1 * 3121 + i1 * 45238971 ^ l * l * 418711 + l * 13761));
            pos.setPos(i1, l1, l);
            boolean cold = !(biome.getTemperature(pos) >= 0.15F);
            if (!drawing) {
               drawing = true;
               mc.getTextureManager().bindTexture(locationRainPng);
               buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
            }
            pos.setPos(i1, j2, l);
            int light = world.getCombinedLight(pos, 0);
            float u0;
            float u1;
            float vOff;
            int alpha = 255;
            if (cold) {
               float f10 = ((float)(this.rendererUpdateCount + i1 * i1 * 3121 + i1 * 45238971 + l * l * 418711 + l * 13761 & 31) + partialTicks) / 32.0F * (3.0F + this.random.nextFloat());
               u0 = 0.0F;
               u1 = 1.0F;
               vOff = f10;
            } else {
               float f10 = ((float)(this.rendererUpdateCount & 511) + partialTicks) / 512.0F;
               float f16 = this.random.nextFloat() + f5 * 0.01F * (float)this.random.nextGaussian();
               float f11 = this.random.nextFloat() + f5 * (float)this.random.nextGaussian() * 0.001F;
               u0 = f16;
               u1 = 1.0F + f16;
               vOff = f10 + f11;
               light = (light * 3 + 15728880) / 4;
               if (tl <= 100.0D) {
                  alpha = (int)(tl / 100.0D * 255.0D);
               }
               if (tl >= 4700.0D) {
                  alpha = (int)((4800.0D - tl) / 100.0D * 255.0D);
               }
            }
            int lm1 = light >> 16 & 65535;
            int lm2 = light & 65535;
            buffer.setTranslation(-d0, -d1, -d2);
            buffer.pos((double)((float)i1 - f6) + 0.5D, (double)l1, (double)((float)l - f7) + 0.5D).tex((double)u0, (double)((float)l1 / 4.0F + vOff)).color(cr, cg, cb, alpha).lightmap(lm1, lm2).endVertex();
            buffer.pos((double)((float)i1 + f6) + 0.5D, (double)l1, (double)((float)l + f7) + 0.5D).tex((double)u1, (double)((float)l1 / 4.0F + vOff)).color(cr, cg, cb, alpha).lightmap(lm1, lm2).endVertex();
            buffer.pos((double)((float)i1 + f6) + 0.5D, (double)i2, (double)((float)l + f7) + 0.5D).tex((double)u1, (double)((float)i2 / 4.0F + vOff)).color(cr, cg, cb, alpha).lightmap(lm1, lm2).endVertex();
            buffer.pos((double)((float)i1 - f6) + 0.5D, (double)i2, (double)((float)l - f7) + 0.5D).tex((double)u0, (double)((float)i2 / 4.0F + vOff)).color(cr, cg, cb, alpha).lightmap(lm1, lm2).endVertex();
            buffer.setTranslation(0.0D, 0.0D, 0.0D);
         }
      }
      if (drawing) {
         tessellator.draw();
      }
      GlStateManager.enableCull();
      GlStateManager.disableBlend();
      GlStateManager.alphaFunc(516, 0.1F);
      mc.entityRenderer.disableLightmap();
   }
}
