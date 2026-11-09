package com.thecramp.entity;

import com.thecramp.mob.AdrianBoy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAdrian extends RenderLiving<EntityAdrian> {
   private static final ResourceLocation EntityTexture = new ResourceLocation("ooocraft:textures/models/entityadrian.png");
   protected AdrianBoy model;

   public RenderAdrian(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
      super(renderManager, modelBase, shadowSize);
      this.model = (AdrianBoy)this.mainModel;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityAdrian entity) {
      return EntityTexture;
   }
}
