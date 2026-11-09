package com.thecramp.entity;

import com.thecramp.mob.ModelJamesBaxter;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJamesBaxter extends RenderLiving<EntityJamesBaxter> {
   private static final ResourceLocation EntityTexture = new ResourceLocation("ooocraft:textures/models/entityjamesbaxter.png");
   protected ModelJamesBaxter model;

   public RenderJamesBaxter(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
      super(renderManager, modelBase, shadowSize);
      this.model = (ModelJamesBaxter)this.mainModel;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityJamesBaxter entity) {
      return EntityTexture;
   }
}
