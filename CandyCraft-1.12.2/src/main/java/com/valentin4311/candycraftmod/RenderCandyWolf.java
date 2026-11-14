package com.valentin4311.candycraftmod;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCandyWolf extends RenderWolf {
   private static final ResourceLocation wolfTextures = new ResourceLocation("candycraftmod:textures/entity/wolfcandy.png");
   private static final ResourceLocation tamedWolfTextures = new ResourceLocation("candycraftmod:textures/entity/wolf_tamecandy.png");
   private static final ResourceLocation angryWolfTextures = new ResourceLocation("candycraftmod:textures/entity/wolf_angrycandy.png");
   private static final ResourceLocation tamedWolfTextures2 = new ResourceLocation("candycraftmod:textures/entity/wolf_tamecandy2.png");

   public RenderCandyWolf(RenderManager manager) {
      super(manager);
      this.shadowSize = 0.5F;
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityWolf wolf) {
      if (wolf instanceof EntityCandyWolf) {
         EntityCandyWolf candy = (EntityCandyWolf)wolf;
         return candy.isTamed() ? (candy.getTimeUntilDrink() < 1 ? tamedWolfTextures2 : tamedWolfTextures) : (candy.isAngry() ? angryWolfTextures : wolfTextures);
      }
      return wolf.isTamed() ? tamedWolfTextures : (wolf.isAngry() ? angryWolfTextures : wolfTextures);
   }
}
