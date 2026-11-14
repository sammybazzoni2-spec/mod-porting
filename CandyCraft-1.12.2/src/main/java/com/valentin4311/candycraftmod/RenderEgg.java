package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEgg extends TileEntitySpecialRenderer<TileEntityEgg> {
   @Override
   public void render(TileEntityEgg egg, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(x + 0.5D, y + 0.8D + (double)(200 - egg.timeLeft) / 100.0D, z + 0.5D);
      this.renderLight(egg, partialTicks);
      GlStateManager.popMatrix();
   }

   protected void renderLight(TileEntityEgg egg, float partialTicks) {
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      RenderHelper.disableStandardItemLighting();
      float f1 = ((float)(200 - egg.timeLeft) + partialTicks) / 200.0F;
      float f2 = 0.0F;
      if (200 - egg.timeLeft > 0) {
         if (f1 > 0.8F) {
            f2 = (f1 - 0.8F) / 0.2F;
         }
         Random random = new Random(432L);
         GlStateManager.disableTexture2D();
         GlStateManager.shadeModel(GL11.GL_SMOOTH);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
         GlStateManager.disableAlpha();
         GlStateManager.enableCull();
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         for (int i = 0; (float)i < (f1 + f1 * f1) / 2.0F * 320.0F; ++i) {
            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);
            buffer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
            float f3 = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
            float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
            f3 /= 2.0F;
            f4 /= 2.0F;
            int a = (int)(255.0F * (1.0F - f2));
            buffer.pos(0.0D, 0.0D, 0.0D).color(255, 233, 220, a).endVertex();
            buffer.pos(-0.866D * (double)f4, (double)f3, (double)(-0.5F * f4)).color(252, 190, 215, 0).endVertex();
            buffer.pos(0.866D * (double)f4, (double)f3, (double)(-0.5F * f4)).color(252, 190, 215, 0).endVertex();
            buffer.pos(0.0D, (double)f3, (double)(1.0F * f4)).color(252, 190, 215, 0).endVertex();
            buffer.pos(-0.866D * (double)f4, (double)f3, (double)(-0.5F * f4)).color(252, 190, 215, 0).endVertex();
            tessellator.draw();
         }
         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.shadeModel(GL11.GL_FLAT);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         RenderHelper.enableStandardItemLighting();
      }
   }
}
