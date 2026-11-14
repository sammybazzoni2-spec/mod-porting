package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlchemy extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape12;

   public ModelAlchemy() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 16, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 14, 11, 14);
      this.Shape1.setRotationPoint(-7.0F, 12.0F, -7.0F);
      this.Shape1.setTextureSize(96, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 26);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 10, 3, 2);
      this.Shape2.setRotationPoint(-5.0F, 9.0F, -5.0F);
      this.Shape2.setTextureSize(96, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, -1.570796F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 26);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 10, 3, 2);
      this.Shape3.setRotationPoint(7.0F, 9.0F, -5.0F);
      this.Shape3.setTextureSize(96, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, -1.570796F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 26);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 10, 3, 2);
      this.Shape4.setRotationPoint(-5.0F, 9.0F, -7.0F);
      this.Shape4.setTextureSize(96, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 26);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 10, 3, 2);
      this.Shape5.setRotationPoint(-5.0F, 9.0F, 5.0F);
      this.Shape5.setTextureSize(96, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4);
      this.Shape6.setRotationPoint(-8.0F, 8.0F, 4.0F);
      this.Shape6.setTextureSize(96, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4);
      this.Shape7.setRotationPoint(-8.0F, 8.0F, -8.0F);
      this.Shape7.setTextureSize(96, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4);
      this.Shape8.setRotationPoint(4.0F, 8.0F, 4.0F);
      this.Shape8.setTextureSize(96, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4);
      this.Shape9.setRotationPoint(4.0F, 8.0F, -8.0F);
      this.Shape9.setTextureSize(96, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 48, 0);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 10, 0, 10);
      this.Shape10.setRotationPoint(-5.0F, 10.0F, -5.0F);
      this.Shape10.setTextureSize(96, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 58, 10);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 14, 0, 14);
      this.Shape11.setRotationPoint(-7.0F, 21.0F, -7.0F);
      this.Shape11.setTextureSize(96, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 72, 43);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 14, 0, 14);
      this.Shape12.setRotationPoint(-7.0F, 14.0F, -7.0F);
      this.Shape12.setTextureSize(128, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }

   public void renderAll() {
      float f5 = 0.0625F;
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
   }
}

