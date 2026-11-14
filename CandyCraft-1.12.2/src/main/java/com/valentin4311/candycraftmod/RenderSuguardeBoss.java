package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSuguardeBoss extends RenderLiving<EntityBossSuguard> {
   private static final ResourceLocation T0 = new ResourceLocation("candycraftmod:textures/entity/sugardeboss.png");
   private static final ResourceLocation T1 = new ResourceLocation("candycraftmod:textures/entity/sugardeboss1.png");
   private static final ResourceLocation T2 = new ResourceLocation("candycraftmod:textures/entity/sugardeboss2.png");
   private static final ResourceLocation T3 = new ResourceLocation("candycraftmod:textures/entity/sugardeboss3.png");
   private static final ResourceLocation T4 = new ResourceLocation("candycraftmod:textures/entity/sugardeboss4.png");

   public RenderSuguardeBoss(RenderManager manager) {
      super(manager, new ModelSuGuard(), 0.5F);
      this.shadowSize = 1.0F;
      this.addLayer(new RenderSuguardLayer(this, CandyCraft.CaramelBow, 1.0F));
   }

   @Override
   protected void preRenderCallback(EntityBossSuguard entity, float partialTickTime) {
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityBossSuguard entity) {
      return !entity.getAwake() ? T1 : (entity.getStats() == 0 ? T0 : (entity.getStats() == 1 ? T2 : (entity.getStats() == 2 ? T3 : T4)));
   }
}
