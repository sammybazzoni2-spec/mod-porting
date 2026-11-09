package com.thecramp.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFinnParadox extends RenderBiped<EntityFinnParadox> {
   private static final ResourceLocation textureLocation = new ResourceLocation("ooocraft:textures/models/entityfinnparadox.png");

   public RenderFinnParadox(RenderManager renderManager, ModelBiped model, float shadowSize) {
      super(renderManager, model, shadowSize);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityFinnParadox entity) {
      return textureLocation;
   }
}
