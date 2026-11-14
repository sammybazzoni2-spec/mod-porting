package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCandyCreeper extends RenderCreeper {
   private static final ResourceLocation creeperTextures = new ResourceLocation("candycraftmod:textures/entity/candycreeper.png");

   public RenderCandyCreeper(RenderManager manager) {
      super(manager);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityCreeper entity) {
      return creeperTextures;
   }
}
