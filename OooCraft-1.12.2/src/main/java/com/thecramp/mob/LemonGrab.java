package com.thecramp.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class LemonGrab extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Head;
   ModelRenderer Body;
   ModelRenderer RightArm;
   ModelRenderer LeftArm;
   ModelRenderer RightLeg;
   ModelRenderer LeftLeg;
   ModelRenderer Shape4;

   public LemonGrab() {
      this.textureWidth = 64;
      this.textureHeight = 128;
      this.Shape1 = new ModelRenderer(this, 24, 29);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shape1.setRotationPoint(-2.0F, -15.0F, -2.0F);
      this.Shape1.setTextureSize(64, 128);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 20);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6);
      this.Shape2.setRotationPoint(-3.0F, -14.0F, -3.0F);
      this.Shape2.setTextureSize(64, 128);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 27);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6);
      this.Shape3.setRotationPoint(-3.0F, -3.0F, -3.0F);
      this.Shape3.setTextureSize(64, 128);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Head = new ModelRenderer(this, 0, 0);
      this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8);
      this.Head.setRotationPoint(0.0F, -5.0F, 0.0F);
      this.Head.setTextureSize(64, 128);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 32, 0);
      this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 14, 4);
      this.Body.setRotationPoint(0.0F, -2.0F, 0.0F);
      this.Body.setTextureSize(64, 128);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.RightArm = new ModelRenderer(this, 43, 18);
      this.RightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 15, 2);
      this.RightArm.setRotationPoint(-5.0F, -1.0F, 0.0F);
      this.RightArm.setTextureSize(64, 128);
      this.RightArm.mirror = true;
      this.setRotation(this.RightArm, 0.0F, 0.0F, 0.0F);
      this.LeftArm = new ModelRenderer(this, 43, 18);
      this.LeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 15, 2);
      this.LeftArm.setRotationPoint(5.0F, -1.0F, 0.0F);
      this.LeftArm.setTextureSize(64, 128);
      this.setRotation(this.LeftArm, 0.0F, 0.0F, 0.0F);
      this.LeftArm.mirror = false;
      this.RightLeg = new ModelRenderer(this, 56, 0);
      this.RightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2);
      this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
      this.RightLeg.setTextureSize(64, 128);
      this.RightLeg.mirror = true;
      this.setRotation(this.RightLeg, 0.0F, 0.0F, 0.0F);
      this.LeftLeg = new ModelRenderer(this, 56, 0);
      this.LeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2);
      this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
      this.LeftLeg.setTextureSize(64, 128);
      this.setRotation(this.LeftLeg, 0.0F, 0.0F, 0.0F);
      this.LeftLeg.mirror = false;
      this.Shape4 = new ModelRenderer(this, 0, 34);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 6);
      this.Shape4.setRotationPoint(-0.5F, -7.0F, -10.0F);
      this.Shape4.setTextureSize(64, 128);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0174533F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Head.render(f5);
      this.Body.render(f5);
      this.RightArm.render(f5);
      this.LeftArm.render(f5);
      this.RightLeg.render(f5);
      this.LeftLeg.render(f5);
      this.Shape4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
      this.RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
   }
}
