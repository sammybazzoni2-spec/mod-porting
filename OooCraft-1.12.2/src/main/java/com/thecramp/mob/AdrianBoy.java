package com.thecramp.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class AdrianBoy extends ModelBase {
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer head;
   ModelRenderer body;
   ModelRenderer leg1;
   ModelRenderer leg2;
   ModelRenderer arm2;
   ModelRenderer arm1;

   public AdrianBoy() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 10, 10, 10);
      this.head.setRotationPoint(-1.0F, 4.0F, -1.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.body = new ModelRenderer(this, 40, 0);
      this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
      this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
      this.body.setTextureSize(64, 32);
      this.body.mirror = true;
      this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
      this.leg1 = new ModelRenderer(this, 30, 24);
      this.leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
      this.leg1.setRotationPoint(-2.0F, 18.0F, 0.0F);
      this.leg1.setTextureSize(64, 32);
      this.leg1.mirror = true;
      this.setRotation(this.leg1, 0.0F, 0.0174533F, 0.0F);
      this.leg2 = new ModelRenderer(this, 22, 24);
      this.leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
      this.leg2.setRotationPoint(2.0F, 18.0F, 0.0F);
      this.leg2.setTextureSize(64, 32);
      this.leg2.mirror = true;
      this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
      this.arm2 = new ModelRenderer(this, 8, 22);
      this.arm2.addBox(-2.0F, 0.0F, -1.0F, 2, 8, 2);
      this.arm2.setRotationPoint(-4.0F, 7.0F, 0.0F);
      this.arm2.setTextureSize(64, 32);
      this.arm2.mirror = true;
      this.setRotation(this.arm2, 0.0F, 0.0F, 0.0F);
      this.arm1 = new ModelRenderer(this, 0, 22);
      this.arm1.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2);
      this.arm1.setRotationPoint(4.0F, 7.0F, 0.0F);
      this.arm1.setTextureSize(64, 32);
      this.arm1.mirror = true;
      this.setRotation(this.arm2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.head.render(f5);
      this.body.render(f5);
      this.leg1.render(f5);
      this.leg2.render(f5);
      this.arm2.render(f5);
      this.arm1.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.arm2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
   }
}
