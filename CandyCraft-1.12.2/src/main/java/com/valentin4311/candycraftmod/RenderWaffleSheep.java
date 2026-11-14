package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWaffleSheep extends RenderLiving<EntityWaffleSheep> {
   private static final ResourceLocation shearedSheepTextures = new ResourceLocation("candycraftmod:textures/entity/sheepcandy0.png");
   private static final ResourceLocation shearedSheepTextures2 = new ResourceLocation("candycraftmod:textures/entity/sheepcandy1.png");
   private static final ResourceLocation sheepTextures = new ResourceLocation("candycraftmod:textures/entity/sheepcandy.png");

   public RenderWaffleSheep(RenderManager manager, ModelBase model, final ModelWaffleSheep woolModel, float shadow) {
      super(manager, model, shadow);
      this.addLayer(new LayerRenderer<EntityWaffleSheep>() {
         @Override
         public void doRenderLayer(EntityWaffleSheep sheep, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (sheep.isInvisible()) {
               return;
            }
            RenderWaffleSheep.this.bindTexture(sheepTextures);
            woolModel.woolSize = -0.15F - (float)sheep.getWool() * 0.02F;
            float off = sheep.isChild() ? -woolModel.woolSize / 2.0F : -woolModel.woolSize;
            woolModel.leg1.offsetY = off;
            woolModel.leg2.offsetY = off;
            woolModel.leg3.offsetY = off;
            woolModel.leg4.offsetY = off;
            woolModel.body.offsetY = off;
            woolModel.head.offsetY = off;
            woolModel.setModelAttributes(RenderWaffleSheep.this.getMainModel());
            woolModel.setLivingAnimations(sheep, limbSwing, limbSwingAmount, partialTicks);
            woolModel.render(sheep, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
         }

         @Override
         public boolean shouldCombineTextures() {
            return true;
         }
      });
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityWaffleSheep sheep) {
      return sheep.hurtTime > 0 ? shearedSheepTextures2 : shearedSheepTextures;
   }
}
