package com.thecramp.entity;

import com.thecramp.mob.ModelLich;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLich extends RenderLiving<EntityLich> {
   private static final ResourceLocation EntityTexture = new ResourceLocation("ooocraft:textures/models/entitylich.png");
   protected ModelLich model;

   public RenderLich(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
      super(renderManager, modelBase, shadowSize);
      this.model = (ModelLich)this.mainModel;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityLich entity) {
      return EntityTexture;
   }
}
