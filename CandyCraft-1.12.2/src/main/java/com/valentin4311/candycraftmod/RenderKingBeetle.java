package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKingBeetle extends RenderLiving<EntityKingBeetle> {
   private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/tamedbeetle.png");

   public RenderKingBeetle(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   protected void preRenderCallback(EntityKingBeetle entity, float partialTickTime) {
      GlStateManager.scale(3.0F, 3.0F, 3.0F);
      GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityKingBeetle entity) {
      return texture;
   }
}
