package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNessie2 extends ModelBase {
   ModelRenderer Body;
   ModelRenderer RearEnd;
   ModelRenderer Leg8;
   ModelRenderer Leg2;
   ModelRenderer Leg7;
   ModelRenderer Leg1;
   ModelRenderer RearEnd1;
   ModelRenderer RearEnd2;
   ModelRenderer RearEnd3;
   ModelRenderer RearEnd4;

   public ModelNessie2(float var1) {
      this.textureWidth = 128;
      this.textureHeight = 128;
      this.RearEnd = new ModelRenderer(this, 0, 33);
      this.RearEnd.addBox(-5.0F, -4.0F, -12.0F, 9, 9, 9, var1);
      this.RearEnd.setRotationPoint(0.0F, 19.0F, 2.0F);
      this.RearEnd.setTextureSize(128, 128);
      this.RearEnd.mirror = true;
      this.setRotation(this.RearEnd, 0.0F, 0.0F, 0.0F);
      this.Leg8 = new ModelRenderer(this, 24, 23);
      this.Leg8.addBox(0.0F, -1.0F, -1.0F, 9, 2, 3, var1);
      this.Leg8.setRotationPoint(3.0F, 22.0F, -8.0F);
      this.Leg8.setTextureSize(64, 32);
      this.Leg8.mirror = true;
      this.setRotation(this.Leg8, 0.0F, 0.5759587F, 0.1919862F);
      this.Leg2 = new ModelRenderer(this, 24, 23);
      this.Leg2.addBox(0.0F, -1.0F, -1.0F, 9, 2, 3, var1);
      this.Leg2.setRotationPoint(2.0F, 22.0F, 6.0F);
      this.Leg2.setTextureSize(128, 128);
      this.Leg2.mirror = true;
      this.setRotation(this.Leg2, 0.0F, -0.5759587F, 0.1919862F);
      this.Leg7 = new ModelRenderer(this, 24, 18);
      this.Leg7.addBox(-9.0F, -1.0F, -1.0F, 9, 2, 3, var1);
      this.Leg7.setRotationPoint(-4.0F, 22.0F, -8.0F);
      this.Leg7.setTextureSize(128, 128);
      this.Leg7.mirror = true;
      this.setRotation(this.Leg7, 0.0F, -0.5759587F, -0.1919862F);
      this.Leg1 = new ModelRenderer(this, 24, 18);
      this.Leg1.addBox(-9.0F, -1.0F, -2.0F, 9, 2, 3, var1);
      this.Leg1.setRotationPoint(-3.0F, 22.0F, 7.0F);
      this.Leg1.setTextureSize(128, 128);
      this.Leg1.mirror = true;
      this.setRotation(this.Leg1, 0.0F, 0.5759587F, -0.1919862F);
      this.RearEnd1 = new ModelRenderer(this, 0, 52);
      this.RearEnd1.addBox(-5.0F, -5.0F, 0.0F, 7, 7, 9, var1);
      this.RearEnd1.setRotationPoint(1.0F, 21.0F, -1.0F);
      this.RearEnd1.setTextureSize(128, 128);
      this.RearEnd1.mirror = true;
      this.setRotation(this.RearEnd1, 0.0F, 0.0F, 0.0F);
      this.RearEnd2 = new ModelRenderer(this, 0, 69);
      this.RearEnd2.addBox(-3.0F, -3.0F, 0.0F, 5, 5, 7, var1);
      this.RearEnd2.setRotationPoint(0.0F, 20.0F, 8.0F);
      this.RearEnd2.setTextureSize(128, 128);
      this.RearEnd2.mirror = true;
      this.setRotation(this.RearEnd2, 0.0F, 0.0F, 0.0F);
      this.RearEnd3 = new ModelRenderer(this, 0, 89);
      this.RearEnd3.addBox(-1.0F, -1.0F, 0.0F, 3, 3, 5, var1);
      this.RearEnd3.setRotationPoint(-1.0F, 19.0F, 15.0F);
      this.RearEnd3.setTextureSize(128, 128);
      this.RearEnd3.mirror = true;
      this.setRotation(this.RearEnd3, 0.0F, 0.0F, 0.0F);
      this.RearEnd4 = new ModelRenderer(this, 0, 82);
      this.RearEnd4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, var1);
      this.RearEnd4.setRotationPoint(-1.0F, 19.0F, 20.0F);
      this.RearEnd4.setTextureSize(128, 128);
      this.RearEnd4.mirror = true;
      this.setRotation(this.RearEnd4, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.RearEnd.render(f5);
      this.Leg8.render(f5);
      this.Leg2.render(f5);
      this.Leg7.render(f5);
      this.Leg1.render(f5);
      this.RearEnd1.render(f5);
      this.RearEnd2.render(f5);
      this.RearEnd3.render(f5);
      this.RearEnd4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      EntityNessie entityT = (EntityNessie)entity;
      this.Leg7.rotateAngleY = MathHelper.cos(entityT.current * 0.8F);
      this.Leg1.rotateAngleY = this.Leg7.rotateAngleY;
      float i = entityT.current + 4.0F;
      this.Leg8.rotateAngleY = MathHelper.cos(i * 0.8F);
      this.Leg2.rotateAngleY = this.Leg8.rotateAngleY;
   }
}

