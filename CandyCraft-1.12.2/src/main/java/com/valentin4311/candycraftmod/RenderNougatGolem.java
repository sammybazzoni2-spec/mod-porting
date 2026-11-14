package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNougatGolem extends RenderLiving<EntityNougatGolem> {
   private static final ResourceLocation body = new ResourceLocation("candycraftmod:textures/entity/nougatgolem2.png");
   private static final ResourceLocation head = new ResourceLocation("candycraftmod:textures/entity/nougatgolem.png");

   public RenderNougatGolem(RenderManager manager) {
      super(manager, new ModelNougatGolem(), 1.0F);
      this.shadowSize = 0.75F;
   }

   @Override
   protected void preRenderCallback(EntityNougatGolem entity, float partialTickTime) {
      float f = entity.getLenght();
      GlStateManager.scale(f, f, f);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityNougatGolem entity) {
      return entity.isTop() ? head : body;
   }
}
