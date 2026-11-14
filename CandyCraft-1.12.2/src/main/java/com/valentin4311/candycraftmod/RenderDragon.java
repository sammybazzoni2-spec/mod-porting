package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDragon extends RenderLiving<EntityDragon> {
   private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/entity/dragons.png");
   private double heightOverlay;

   public RenderDragon(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   public void doRender(EntityDragon entity, double x, double y, double z, float entityYaw, float partialTicks) {
      ModelDragon model = (ModelDragon)this.mainModel;
      boolean ridden = entity.isBeingRidden();
      model.RightWing.isHidden = !ridden;
      model.LeftWing.isHidden = !ridden;
      model.RightScale.isHidden = !ridden;
      model.LeftScale.isHidden = !ridden;
      this.heightOverlay = ridden ? Math.sin((double)((float)entity.ticksExisted * 0.05F)) / 6.0D : 0.0D;
      super.doRender(entity, x, y + this.heightOverlay, z, entityYaw, partialTicks);
   }

   @Override
   protected void preRenderCallback(EntityDragon entity, float partialTickTime) {
      GlStateManager.translate(-0.0625F, 0.0F, 0.0F);
      GlStateManager.scale(1.5F, 1.5F, 1.5F);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityDragon entity) {
      return texture;
   }
}
