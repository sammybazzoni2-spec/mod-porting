package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCottonSpider extends ModelBase {
   ModelRenderer Head;
   ModelRenderer Body;
   ModelRenderer RearEnd;
   ModelRenderer Leg8;
   ModelRenderer Leg6;
   ModelRenderer Leg4;
   ModelRenderer Leg2;
   ModelRenderer Leg7;
   ModelRenderer Leg5;
   ModelRenderer Leg3;
   ModelRenderer Leg1;

   public ModelCottonSpider() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Head = new ModelRenderer(this, 32, 4);
      this.Head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
      this.Head.setRotationPoint(0.0F, 20.0F, -3.0F);
      this.Head.setTextureSize(64, 32);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
      this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.Body.setTextureSize(64, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.RearEnd = new ModelRenderer(this, 0, 12);
      this.RearEnd.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12);
      this.RearEnd.setRotationPoint(0.0F, 18.0F, 3.0F);
      this.RearEnd.setTextureSize(64, 32);
      this.RearEnd.mirror = true;
      this.setRotation(this.RearEnd, 1.570796F, 0.0F, 0.0F);
      this.Leg1 = new ModelRenderer(this, 18, 0);
      this.Leg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg1.setRotationPoint(-4.0F, 15.0F, 2.0F);
      this.Leg2 = new ModelRenderer(this, 18, 0);
      this.Leg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg2.setRotationPoint(4.0F, 15.0F, 2.0F);
      this.Leg3 = new ModelRenderer(this, 18, 0);
      this.Leg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg3.setRotationPoint(-4.0F, 15.0F, 1.0F);
      this.Leg4 = new ModelRenderer(this, 18, 0);
      this.Leg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg4.setRotationPoint(4.0F, 15.0F, 1.0F);
      this.Leg5 = new ModelRenderer(this, 18, 0);
      this.Leg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg5.setRotationPoint(-4.0F, 15.0F, 0.0F);
      this.Leg6 = new ModelRenderer(this, 18, 0);
      this.Leg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg6.setRotationPoint(4.0F, 15.0F, 0.0F);
      this.Leg7 = new ModelRenderer(this, 18, 0);
      this.Leg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg7.setRotationPoint(-4.0F, 15.0F, -1.0F);
      this.Leg8 = new ModelRenderer(this, 18, 0);
      this.Leg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
      this.Leg8.setRotationPoint(4.0F, 15.0F, -1.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, 0.0F, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Head.render(f5);
      this.Body.render(f5);
      this.RearEnd.render(f5);
      this.Leg8.render(f5);
      this.Leg6.render(f5);
      this.Leg4.render(f5);
      this.Leg2.render(f5);
      this.Leg7.render(f5);
      this.Leg5.render(f5);
      this.Leg3.render(f5);
      this.Leg1.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
      float f6 = ((float)Math.PI / 4F);
      this.Leg1.rotateAngleZ = -f6;
      this.Leg2.rotateAngleZ = f6;
      this.Leg3.rotateAngleZ = -f6 * 0.74F;
      this.Leg4.rotateAngleZ = f6 * 0.74F;
      this.Leg5.rotateAngleZ = -f6 * 0.74F;
      this.Leg6.rotateAngleZ = f6 * 0.74F;
      this.Leg7.rotateAngleZ = -f6;
      this.Leg8.rotateAngleZ = f6;
      float f7 = -0.0F;
      float f8 = ((float)Math.PI / 8F);
      this.Leg1.rotateAngleY = f8 * 2.0F + f7;
      this.Leg2.rotateAngleY = -f8 * 2.0F - f7;
      this.Leg3.rotateAngleY = f8 * 1.0F + f7;
      this.Leg4.rotateAngleY = -f8 * 1.0F - f7;
      this.Leg5.rotateAngleY = -f8 * 1.0F + f7;
      this.Leg6.rotateAngleY = f8 * 1.0F - f7;
      this.Leg7.rotateAngleY = -f8 * 2.0F + f7;
      this.Leg8.rotateAngleY = f8 * 2.0F - f7;
      float f9 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
      float f10 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * par2;
      float f11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * par2;
      float f12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * par2;
      float f13 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
      float f14 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float)Math.PI) * 0.4F) * par2;
      float f15 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * par2;
      float f16 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * par2;
      ModelRenderer var10000 = this.Leg1;
      var10000.rotateAngleY += f9;
      var10000 = this.Leg2;
      var10000.rotateAngleY += -f9;
      var10000 = this.Leg3;
      var10000.rotateAngleY += f10;
      var10000 = this.Leg4;
      var10000.rotateAngleY += -f10;
      var10000 = this.Leg5;
      var10000.rotateAngleY += f11;
      var10000 = this.Leg6;
      var10000.rotateAngleY += -f11;
      var10000 = this.Leg7;
      var10000.rotateAngleY += f12;
      var10000 = this.Leg8;
      var10000.rotateAngleY += -f12;
      var10000 = this.Leg1;
      var10000.rotateAngleZ += f13;
      var10000 = this.Leg2;
      var10000.rotateAngleZ += -f13;
      var10000 = this.Leg3;
      var10000.rotateAngleZ += f14;
      var10000 = this.Leg4;
      var10000.rotateAngleZ += -f14;
      var10000 = this.Leg5;
      var10000.rotateAngleZ += f15;
      var10000 = this.Leg6;
      var10000.rotateAngleZ += -f15;
      var10000 = this.Leg7;
      var10000.rotateAngleZ += f16;
      var10000 = this.Leg8;
      var10000.rotateAngleZ += -f16;
   }
}

