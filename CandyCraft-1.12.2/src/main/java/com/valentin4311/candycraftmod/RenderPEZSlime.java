package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPEZSlime extends RenderLiving<EntityPEZSlime> {
   private static final ResourceLocation TEX = new ResourceLocation("candycraftmod:textures/entity/candyboss5.png");
   private static final ResourceLocation SLEEP = new ResourceLocation("candycraftmod:textures/entity/candyboss4.png");

   public RenderPEZSlime(RenderManager manager, ModelBase model, ModelBase outer, float shadow) {
      super(manager, model, shadow);
      this.addLayer(new RenderJellyLayer(this, outer));
   }

   @Override
   protected void preRenderCallback(EntityPEZSlime entity, float partialTickTime) {
      float f1 = (float)entity.getSlimeSize();
      float f2 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityPEZSlime entity) {
      return entity.getAwake() == 0 ? SLEEP : TEX;
   }
}
