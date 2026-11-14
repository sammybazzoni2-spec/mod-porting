package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCottonSpider extends RenderLiving<EntityCottonCandySpider> {
   private static final ResourceLocation spiderTextures = new ResourceLocation("candycraftmod:textures/entity/cottonspider.png");

   public RenderCottonSpider(RenderManager manager) {
      super(manager, new ModelCottonSpider(), 1.0F);
   }

   @Override
   protected float getDeathMaxRotation(EntityCottonCandySpider entity) {
      return 180.0F;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityCottonCandySpider entity) {
      return spiderTextures;
   }
}
