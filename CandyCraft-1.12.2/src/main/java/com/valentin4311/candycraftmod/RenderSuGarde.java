package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSuGarde extends RenderLiving<EntitySuguard> {
   private static final ResourceLocation TEX = new ResourceLocation("candycraftmod:textures/entity/sugarde.png");

   public RenderSuGarde(RenderManager manager) {
      super(manager, new ModelSuGuard(), 0.5F);
      this.shadowSize = 0.5F;
      this.addLayer(new RenderSuguardLayer(this, CandyCraft.LicoriceSpear, 1.0F));
   }

   @Override
   protected ResourceLocation getEntityTexture(EntitySuguard entity) {
      return TEX;
   }
}
