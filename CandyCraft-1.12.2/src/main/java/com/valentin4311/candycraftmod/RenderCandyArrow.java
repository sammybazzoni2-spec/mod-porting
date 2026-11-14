package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCandyArrow extends RenderArrow<EntityCandyArrow> {
   private static final ResourceLocation arrowTextures = new ResourceLocation("candycraftmod:textures/entity/candyarrow.png");
   private static final ResourceLocation boltTextures = new ResourceLocation("candycraftmod:textures/entity/candybolt.png");

   public RenderCandyArrow(RenderManager manager) {
      super(manager);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityCandyArrow entity) {
      return entity.isBolt() ? boltTextures : arrowTextures;
   }
}
