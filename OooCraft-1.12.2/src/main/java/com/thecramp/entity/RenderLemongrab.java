package com.thecramp.entity;

import com.thecramp.mob.LemonGrab;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLemongrab extends RenderLiving<EntityLemongrab> {
   private static final ResourceLocation EntityTexture = new ResourceLocation("ooocraft:textures/models/entitylemongrab.png");
   protected LemonGrab model;

   public RenderLemongrab(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
      super(renderManager, modelBase, shadowSize);
      this.model = (LemonGrab)this.mainModel;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityLemongrab entity) {
      return EntityTexture;
   }
}
