package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCandyPig extends RenderPig {
   private static final ResourceLocation pigTextures = new ResourceLocation("candycraftmod:textures/entity/candypig.png");

   public RenderCandyPig(RenderManager manager) {
      super(manager);
      this.mainModel = new ModelCandyPig();
      this.shadowSize = 0.7F;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityPig entity) {
      return pigTextures;
   }
}
