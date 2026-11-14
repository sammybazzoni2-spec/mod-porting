package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCandySlime extends RenderLiving<EntityCandySlime> {
   private static final ResourceLocation T1 = new ResourceLocation("candycraftmod:textures/entity/candyboss.png");
   private static final ResourceLocation T2 = new ResourceLocation("candycraftmod:textures/entity/candyboss2.png");
   private static final ResourceLocation T3 = new ResourceLocation("candycraftmod:textures/entity/candyboss3.png");
   private static final ResourceLocation T4 = new ResourceLocation("candycraftmod:textures/entity/candyboss4.png");

   public RenderCandySlime(RenderManager manager, ModelBase model, ModelBase outer, float shadow) {
      super(manager, model, shadow);
      this.addLayer(new RenderJellyLayer(this, outer));
   }

   @Override
   protected void preRenderCallback(EntityCandySlime entity, float partialTickTime) {
      float f1 = (float)entity.getSlimeSize();
      float f2 = (entity.prevSquishFactor + (entity.squishFactor - entity.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityCandySlime entity) {
      return entity.getAwake() == 0 ? T4 : (entity.getStats() == 0 ? T1 : (entity.getStats() == 1 ? T3 : T2));
   }
}
