package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFish extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape7;
   ModelRenderer Shape6;
   ModelRenderer Shape5;

   public ModelFish() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 5, 9);
      this.Shape1.setRotationPoint(-1.0F, 16.0F, -4.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 8, 14);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.Shape2.setRotationPoint(-0.5F, 18.23333F, 3.266667F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.7807508F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 22, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.Shape3.setRotationPoint(-0.5F, 15.0F, -2.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 14);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4);
      this.Shape4.setRotationPoint(1.09F, 18.0F, 0.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 14);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4);
      this.Shape7.setRotationPoint(-1.1F, 17.7F, -1.0F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 8, 14);
      this.Shape6.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 5);
      this.Shape6.setRotationPoint(0.0F, 17.0F, 3.866667F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, -0.7807508F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 20);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
      this.Shape5.setRotationPoint(-1.0F, 18.0F, -5.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape7.render(f5);
      this.Shape6.render(f5);
      this.Shape5.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
      super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
      EntityFish entity = (EntityFish)par7Entity;
      if (par7Entity.motionX == (double)0.0F && par7Entity.motionY == (double)0.0F && par7Entity.motionZ == (double)0.0F) {
         this.Shape2.rotateAngleY = 0.0F;
         this.Shape6.rotateAngleY = 0.0F;
      } else {
         this.Shape2.rotateAngleY = MathHelper.cos(entity.current * 2.3662F);
         this.Shape6.rotateAngleY = this.Shape2.rotateAngleY;
      }

   }
}

