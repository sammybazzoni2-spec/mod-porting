package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBunny extends RenderLiving<EntityBunny> {
   private static final ResourceLocation overlayTextures = new ResourceLocation("candycraftmod:textures/entity/whitebunny.png");
   private static final ResourceLocation bunnyTextures = new ResourceLocation("candycraftmod:textures/entity/bunny.png");

   public RenderBunny(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
      this.addLayer(new LayerRenderer<EntityBunny>() {
         @Override
         public void doRenderLayer(EntityBunny bunny, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (!bunny.isInvisible()) {
               RenderBunny.this.bindTexture(overlayTextures);
               GlStateManager.color((float)((double)bunny.getRed() / 255.0D), (float)((double)bunny.getGreen() / 255.0D), (float)((double)bunny.getBlue() / 255.0D));
               RenderBunny.this.getMainModel().setModelAttributes(RenderBunny.this.getMainModel());
               RenderBunny.this.getMainModel().render(bunny, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
               GlStateManager.color(1.0F, 1.0F, 1.0F);
            }
         }

         @Override
         public boolean shouldCombineTextures() {
            return false;
         }
      });
   }

   @Override
   protected void preRenderCallback(EntityBunny entity, float partialTickTime) {
      if (entity.isChild()) {
         GlStateManager.scale(0.7F, 0.7F, 0.7F);
      }
   }

   @Override
   public void doRender(EntityBunny entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityBunny entity) {
      return bunnyTextures;
   }
}
