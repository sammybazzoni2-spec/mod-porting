package com.thecramp.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelJamesBaxter extends ModelBase {
   ModelRenderer head;
   ModelRenderer nose;
   ModelRenderer earr;
   ModelRenderer earl;
   ModelRenderer upperbody;
   ModelRenderer neck;
   ModelRenderer lowerbody;
   ModelRenderer legl;
   ModelRenderer legr;
   ModelRenderer beachball;
   ModelRenderer arml;
   ModelRenderer armr;
   ModelRenderer mane;
   ModelRenderer tail;

   public ModelJamesBaxter() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.head = new ModelRenderer(this, 0, 31);
      this.head.addBox(-2.0F, -4.0F, -2.5F, 4, 4, 5);
      this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, -0.1487144F, 0.0F, 0.0F);
      this.nose = new ModelRenderer(this, 0, 40);
      this.nose.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
      this.nose.setRotationPoint(0.0F, -2.0F, -2.0F);
      this.nose.setTextureSize(64, 32);
      this.nose.mirror = true;
      this.setRotation(this.nose, 0.0F, 0.0F, 0.0F);
      this.earr = new ModelRenderer(this, 0, 46);
      this.earr.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1);
      this.earr.setRotationPoint(2.0F, -3.0F, 2.0F);
      this.earr.setTextureSize(64, 32);
      this.earr.mirror = true;
      this.setRotation(this.earr, 0.0F, 0.0F, 0.0F);
      this.earl = new ModelRenderer(this, 0, 50);
      this.earl.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1);
      this.earl.setRotationPoint(-2.0F, -3.0F, 2.0F);
      this.earl.setTextureSize(64, 32);
      this.earl.mirror = true;
      this.setRotation(this.earl, 0.0F, 0.0F, 0.0F);
      this.upperbody = new ModelRenderer(this, 0, 17);
      this.upperbody.addBox(-3.0F, -0.5F, -3.5F, 6, 7, 7);
      this.upperbody.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.upperbody.setTextureSize(64, 32);
      this.upperbody.mirror = true;
      this.setRotation(this.upperbody, 0.0F, 0.0F, 0.0F);
      this.neck = new ModelRenderer(this, 6, 47);
      this.neck.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
      this.neck.setRotationPoint(0.0F, -0.5F, 0.0F);
      this.neck.setTextureSize(64, 32);
      this.neck.mirror = true;
      this.setRotation(this.neck, 0.0F, 0.0F, 0.0F);
      this.lowerbody = new ModelRenderer(this, 28, 46);
      this.lowerbody.addBox(-3.5F, -0.5F, -4.0F, 7, 7, 8);
      this.lowerbody.setRotationPoint(0.0F, 7.0F, 0.0F);
      this.lowerbody.setTextureSize(64, 32);
      this.lowerbody.mirror = true;
      this.setRotation(this.lowerbody, 0.0F, 0.0F, 0.0F);
      this.legl = new ModelRenderer(this, 41, 0);
      this.legl.addBox(0.0F, -0.5F, -0.5F, 2, 8, 1);
      this.legl.setRotationPoint(3.0F, 11.0F, 0.0F);
      this.legl.setTextureSize(64, 32);
      this.legl.mirror = true;
      this.setRotation(this.legl, -0.2974289F, 0.0F, 0.0F);
      this.legr = new ModelRenderer(this, 50, 0);
      this.legr.addBox(-2.0F, 0.0F, -0.5F, 2, 8, 1);
      this.legr.setRotationPoint(-3.0F, 11.0F, 0.0F);
      this.legr.setTextureSize(64, 32);
      this.legr.mirror = true;
      this.setRotation(this.legr, 0.2974289F, 0.0F, 0.0F);
      this.beachball = new ModelRenderer(this, 0, 0);
      this.beachball.addBox(-5.0F, -3.5F, -5.0F, 10, 6, 10);
      this.beachball.setRotationPoint(0.0F, 21.0F, 0.0F);
      this.beachball.setTextureSize(64, 32);
      this.beachball.mirror = true;
      this.setRotation(this.beachball, 0.0F, 0.0F, 0.0F);
      this.arml = new ModelRenderer(this, 28, 20);
      this.arml.addBox(0.5F, 0.0F, -0.5F, 1, 7, 1);
      this.arml.setRotationPoint(2.55F, 3.0F, 0.0F);
      this.arml.setTextureSize(64, 32);
      this.arml.mirror = true;
      this.setRotation(this.arml, -1.301251F, 0.0F, 0.0F);
      this.armr = new ModelRenderer(this, 36, 20);
      this.armr.addBox(-1.0F, 0.0F, -0.5F, 1, 7, 1);
      this.armr.setRotationPoint(-3.1F, 3.0F, 0.0F);
      this.armr.setTextureSize(64, 32);
      this.armr.mirror = true;
      this.setRotation(this.armr, -1.821752F, 0.0F, 0.0F);
      this.mane = new ModelRenderer(this, 0, 54);
      this.mane.addBox(-2.0F, -1.0F, 0.0F, 4, 8, 1);
      this.mane.setRotationPoint(0.0F, -2.0F, 2.5F);
      this.mane.setTextureSize(64, 32);
      this.mane.mirror = true;
      this.setRotation(this.mane, 0.4597453F, 0.0F, 0.0F);
      this.tail = new ModelRenderer(this, 57, 10);
      this.tail.addBox(-1.0F, -1.0F, 0.0F, 2, 9, 1);
      this.tail.setRotationPoint(0.0F, 11.0F, 3.5F);
      this.tail.setTextureSize(64, 32);
      this.tail.mirror = true;
      this.setRotation(this.tail, 0.4214332F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.nose.render(f5);
      this.earr.render(f5);
      this.earl.render(f5);
      this.upperbody.render(f5);
      this.neck.render(f5);
      this.lowerbody.render(f5);
      this.legl.render(f5);
      this.legr.render(f5);
      this.beachball.render(f5);
      this.arml.render(f5);
      this.armr.render(f5);
      this.mane.render(f5);
      this.tail.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.armr.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.arml.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
   }
}
