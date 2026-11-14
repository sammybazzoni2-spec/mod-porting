package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving<EntityBeetle> {
   private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/beetle.png");
   private static final ResourceLocation textureAngry = new ResourceLocation("candycraftmod:textures/entity/angrybeetle.png");

   public RenderBeetle(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   protected void preRenderCallback(EntityBeetle entity, float partialTickTime) {
      if (entity.isChild()) {
         GlStateManager.scale(0.5F, 0.5F, 0.5F);
      }
      GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityBeetle entity) {
      return !entity.isAngry() ? texture : textureAngry;
   }
}
