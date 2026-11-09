package com.thecramp.entity;

import com.thecramp.mob.Gunter;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGunter extends RenderLiving<EntityGunter> {
   private static final ResourceLocation EntityTexture = new ResourceLocation("ooocraft:textures/models/entitygunter.png");
   protected Gunter model;

   public RenderGunter(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
      super(renderManager, modelBase, shadowSize);
      this.model = (Gunter)this.mainModel;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityGunter entity) {
      return EntityTexture;
   }
}
