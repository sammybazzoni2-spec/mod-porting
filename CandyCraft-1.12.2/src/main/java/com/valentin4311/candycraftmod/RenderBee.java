package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBee extends RenderLiving<EntityBee> {
   private static final ResourceLocation beeTextures = new ResourceLocation("candycraftmod:textures/entity/bee.png");

   public RenderBee(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   public void doRender(EntityBee entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
      GlStateManager.disableBlend();
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityBee entity) {
      return beeTextures;
   }
}
