package com.thecramp.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDarren extends RenderBiped<EntityDarren> {
   private static final ResourceLocation textureLocation = new ResourceLocation("ooocraft:textures/models/entitydarren.png");

   public RenderDarren(RenderManager renderManager, ModelBiped model, float shadowSize) {
      super(renderManager, model, shadowSize);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityDarren entity) {
      return textureLocation;
   }

   @Override
   protected void preRenderCallback(EntityDarren entity, float partialTickTime) {
      GlStateManager.scale(10.0F, 10.0F, 10.0F);
   }
}
