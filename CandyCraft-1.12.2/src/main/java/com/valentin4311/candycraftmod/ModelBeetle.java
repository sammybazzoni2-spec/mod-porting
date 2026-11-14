package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBeetle extends ModelBase {
   ModelRenderer leg1;
   ModelRenderer leg2;
   ModelRenderer leg3;
   ModelRenderer leg4;
   ModelRenderer leg5;
   ModelRenderer leg6;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;

   public ModelBeetle() {
      this.textureWidth = 128;
      this.textureHeight = 32;
      this.leg1 = new ModelRenderer(this, 0, 24);
      this.leg1.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.leg1.setRotationPoint(-5.0F, 19.0F, 4.0F);
      this.leg1.setTextureSize(128, 32);
      this.leg1.mirror = true;
      this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
      this.leg2 = new ModelRenderer(this, 0, 24);
      this.leg2.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.leg2.setRotationPoint(0.0F, 19.0F, 4.0F);
      this.leg2.setTextureSize(128, 32);
      this.leg2.mirror = true;
      this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
      this.leg3 = new ModelRenderer(this, 0, 24);
      this.leg3.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.leg3.setRotationPoint(5.0F, 19.0F, 4.0F);
      this.leg3.setTextureSize(128, 32);
      this.leg3.mirror = true;
      this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
      this.leg4 = new ModelRenderer(this, 0, 16);
      this.leg4.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.leg4.setRotationPoint(0.0F, 19.0F, -4.0F);
      this.leg4.setTextureSize(128, 32);
      this.setRotation(this.leg4, 0.0F, 3.141593F, 0.0F);
      this.leg4.mirror = true;
      this.leg5 = new ModelRenderer(this, 0, 16);
      this.leg5.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.leg5.setRotationPoint(-5.0F, 19.0F, -4.0F);
      this.leg5.setTextureSize(128, 32);
      this.setRotation(this.leg5, 0.0F, 3.141593F, 0.0F);
      this.leg5.mirror = true;
      this.leg6 = new ModelRenderer(this, 0, 16);
      this.leg6.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3);
      this.leg6.setRotationPoint(5.0F, 19.0F, -4.0F);
      this.leg6.setTextureSize(128, 32);
      this.setRotation(this.leg6, 0.0F, 3.141593F, 0.0F);
      this.leg6.mirror = true;
      this.Shape1 = new ModelRenderer(this, 46, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 13, 3, 8);
      this.Shape1.setRotationPoint(-6.5F, 18.0F, -4.0F);
      this.Shape1.setTextureSize(128, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 14, 7, 9);
      this.Shape2.setRotationPoint(-7.0F, 11.5F, -4.5F);
      this.Shape2.setTextureSize(128, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 12, 19);
      this.Shape3.addBox(-7.0F, -3.0F, -3.5F, 8, 6, 7);
      this.Shape3.setRotationPoint(-7.0F, 17.0F, 0.0F);
      this.Shape3.setTextureSize(128, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 42, 17);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 8);
      this.Shape4.setRotationPoint(-7.5F, 13.5F, -4.0F);
      this.Shape4.setTextureSize(128, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 60, 18);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 13, 6, 8);
      this.Shape5.setRotationPoint(-6.666667F, 11.7F, -4.0F);
      this.Shape5.setTextureSize(128, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.leg1.render(f5);
      this.leg2.render(f5);
      this.leg3.render(f5);
      this.leg4.render(f5);
      this.leg5.render(f5);
      this.leg6.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity f6) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, f6);
      this.Shape3.rotateAngleZ = -f4 / 85.943665F;
      this.Shape3.rotateAngleY = f3 / 85.943665F;
      this.leg1.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
      this.leg3.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
      this.leg4.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
      this.leg5.rotateAngleZ = -MathHelper.cos(-f * 0.6662F) * 1.0F * f1;
      this.leg6.rotateAngleZ = -MathHelper.cos(-f * 0.6662F) * 1.0F * f1;
      this.leg2.rotateAngleZ = -MathHelper.cos(-f * 0.6662F) * 1.0F * f1;
   }
}

