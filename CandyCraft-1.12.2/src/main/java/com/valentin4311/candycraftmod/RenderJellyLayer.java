package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Translucent outer shell of the slime-like mobs (replacement of the 1.7.10 render pass). */
@SideOnly(Side.CLIENT)
public class RenderJellyLayer implements LayerRenderer<EntityLivingBase> {
   private final RenderLivingBase<?> renderer;
   private final ModelBase outer;

   public RenderJellyLayer(RenderLivingBase<?> renderer, ModelBase outer) {
      this.renderer = renderer;
      this.outer = outer;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entity.isInvisible()) {
         return;
      }
      GlStateManager.enableNormalize();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      this.outer.setModelAttributes(this.renderer.getMainModel());
      this.outer.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      GlStateManager.disableBlend();
      GlStateManager.disableNormalize();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }
}
