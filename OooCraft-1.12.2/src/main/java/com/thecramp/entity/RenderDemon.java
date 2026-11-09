package com.thecramp.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDemon extends RenderBiped<EntityDemon> {
   private static final ResourceLocation textureLocation = new ResourceLocation("ooocraft:textures/models/entitydemon.png");

   public RenderDemon(RenderManager renderManager, ModelBiped model, float shadowSize) {
      super(renderManager, model, shadowSize);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityDemon entity) {
      return textureLocation;
   }

   @Override
   protected void preRenderCallback(EntityDemon entity, float partialTickTime) {
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
   }
}
