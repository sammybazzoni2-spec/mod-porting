package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSuGuard extends ModelBase {
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

   public ModelSuGuard() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 16);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
      this.Shape1.setRotationPoint(1.0F, 20.0F, -1.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 16);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
      this.Shape2.setRotationPoint(-3.0F, 20.0F, -1.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 6);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 6, 6, 4);
      this.Shape3.setRotationPoint(-3.0F, 14.0F, -2.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
      this.Shape4.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 22);
      this.Shape5.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 1);
      this.Shape5.setRotationPoint(0.0F, 12.0F, 0.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 12, 0);
      this.Shape6.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4);
      this.Shape6.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 4, 22);
      this.Shape7.addBox(1.0F, 0.0F, -0.5F, 1, 2, 1);
      this.Shape7.setRotationPoint(0.0F, 12.0F, 0.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 4, 22);
      this.Shape8.addBox(-2.0F, 2.0F, -0.5F, 1, 2, 1);
      this.Shape8.setRotationPoint(0.0F, 10.0F, 0.0F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 20, 6);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2);
      this.Shape9.setRotationPoint(3.0F, 15.0F, 0.0F);
      this.Shape9.setTextureSize(64, 32);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, -1.570796F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 20, 6);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2);
      this.Shape10.setRotationPoint(-4.0F, 15.0F, 0.0F);
      this.Shape10.setTextureSize(64, 32);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, -1.050296F, 0.0F, 0.0F);
      this.Shape10.mirror = false;
      this.Shape11 = new ModelRenderer(this, 8, 16);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 5, 5, 1);
      this.Shape11.setRotationPoint(1.0F, 13.5F, -5.0F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 28, 0);
      this.Shape12.addBox(-1.5F, 0.0F, -1.5F, 3, 1, 3);
      this.Shape12.setRotationPoint(0.0F, 10.0F, 0.0F);
      this.Shape12.setTextureSize(64, 32);
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

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      this.Shape4.rotateAngleY = par1;
      this.Shape5.rotateAngleY = par1;
      this.Shape6.rotateAngleY = par1;
      this.Shape12.rotateAngleY = par1;
      this.Shape7.rotateAngleY = par1;
      this.Shape8.rotateAngleY = par1;
      this.Shape1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.Shape2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
      this.Shape1.rotateAngleY = 0.0F;
      this.Shape2.rotateAngleY = 0.0F;
      if (this.isRiding) {
         this.Shape1.rotateAngleX = -1.5F;
         this.Shape2.rotateAngleX = -1.5F;
      }

   }
}

