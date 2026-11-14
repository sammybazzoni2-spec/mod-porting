package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPingouin extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape9;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;

   public ModelPingouin() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 12);
      this.Shape1.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 4);
      this.Shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.3490659F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 12);
      this.Shape2.addBox(0.0F, 0.0F, -3.0F, 2, 1, 4);
      this.Shape2.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, -0.3490659F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 24, 19);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 4, 9, 4);
      this.Shape3.setRotationPoint(-2.0F, 14.0F, -1.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(-1.0F, 0.0F, -2.0F, 1, 7, 4);
      this.Shape4.setRotationPoint(-2.0F, 14.0F, 1.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.2230717F);
      this.Shape9 = new ModelRenderer(this, 24, 0);
      this.Shape9.addBox(0.0F, 0.0F, -2.0F, 1, 7, 4);
      this.Shape9.setRotationPoint(2.0F, 14.0F, 1.0F);
      this.Shape9.setTextureSize(64, 32);
      this.setRotation(this.Shape9, 0.0F, 0.0F, -0.2230717F);
      this.Shape5 = new ModelRenderer(this, 0, 20);
      this.Shape5.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6);
      this.Shape5.setRotationPoint(0.0F, 14.0F, 1.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 17);
      this.Shape6.addBox(-1.0F, -2.0F, -5.0F, 2, 1, 2);
      this.Shape6.setRotationPoint(0.0F, 14.0F, 1.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 10, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 3, 0);
      this.Shape7.setRotationPoint(-2.0F, 21.0F, 3.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.1487144F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 12, 9);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 5, 10, 1);
      this.Shape8.setRotationPoint(-2.5F, 14.0F, 3.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.2230717F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape9.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape6.rotateAngleX = f4 / (180F / (float)Math.PI);
      this.Shape6.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.Shape5.rotateAngleX = f4 / (180F / (float)Math.PI);
      this.Shape5.rotateAngleY = f3 / (180F / (float)Math.PI);
      this.Shape1.rotateAngleX = (MathHelper.sin(f) + 1.0F) * 0.8F * -1.0F * f1;
      this.Shape2.rotateAngleX = (MathHelper.cos(f) + 1.0F) * 0.8F * -1.0F * f1;
      this.Shape1.rotateAngleY = (MathHelper.sin(f) + 1.0F) * 0.5F * f1 + 0.3490659F;
      this.Shape2.rotateAngleY = (MathHelper.cos(f) + 1.0F) * 0.5F * -1.0F * f1 - 0.3490659F;
      this.Shape8.rotateAngleX = (MathHelper.cos((float)entity.ticksExisted * 0.1F) + 1.0F) * 0.2F;
      if (entity.onGround) {
         this.Shape4.rotateAngleZ = (MathHelper.cos((float)entity.ticksExisted * 0.1F) + 1.0F) * 0.1F + 0.2230717F;
         this.Shape9.rotateAngleZ = -((MathHelper.cos((float)entity.ticksExisted * 0.1F) + 1.0F) * 0.1F) - 0.2230717F;
      } else {
         this.Shape4.rotateAngleZ = (MathHelper.cos((float)entity.ticksExisted * 0.5F) + 1.0F) * 0.6F + 0.2230717F;
         this.Shape9.rotateAngleZ = -((MathHelper.cos((float)entity.ticksExisted * 0.5F) + 1.0F) * 0.6F) - 0.2230717F;
      }

   }
}

