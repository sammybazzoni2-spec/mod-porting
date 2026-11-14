package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityFish extends RenderLiving<EntityFish> {
   private static final ResourceLocation fishTextures = new ResourceLocation("candycraftmod:textures/entity/fish.png");

   public RenderEntityFish(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
      this.shadowSize = 0.3F;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityFish entity) {
      return fishTextures;
   }
}
