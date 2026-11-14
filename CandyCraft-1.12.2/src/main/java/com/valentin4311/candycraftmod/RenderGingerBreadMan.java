package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGingerBreadMan extends RenderBiped<EntityGingerBreadMan> {
   private static final ResourceLocation[] textures = new ResourceLocation[]{new ResourceLocation("candycraftmod:textures/entity/gingerbread0.png"), new ResourceLocation("candycraftmod:textures/entity/gingerbread1.png"), new ResourceLocation("candycraftmod:textures/entity/gingerbread2.png"), new ResourceLocation("candycraftmod:textures/entity/gingerbread3.png")};

   public RenderGingerBreadMan(RenderManager manager, ModelBiped model, float shadow) {
      super(manager, model, shadow);
      this.shadowSize /= 2.0F;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityGingerBreadMan entity) {
      int p = entity.getProfession();
      return textures[Math.max(0, Math.min(textures.length - 1, p))];
   }

   @Override
   protected void preRenderCallback(EntityGingerBreadMan entity, float partialTickTime) {
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
   }
}
