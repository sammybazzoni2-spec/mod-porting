package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBossBeetle extends RenderLiving<EntityBossBeetle> {
   private static final ResourceLocation textureAwake = new ResourceLocation("candycraftmod:textures/entity/bossbeetle.png");
   private static final ResourceLocation textureSleep = new ResourceLocation("candycraftmod:textures/entity/bossbeetle2.png");

   public RenderBossBeetle(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   protected void preRenderCallback(EntityBossBeetle entity, float partialTickTime) {
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityBossBeetle entity) {
      return entity.getAwake() ? textureAwake : textureSleep;
   }
}
