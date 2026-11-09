package com.thecramp.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Gunter extends ModelBase {
   ModelRenderer Body;
   ModelRenderer Arm1;
   ModelRenderer Arm2;
   ModelRenderer Beak;

   public Gunter() {
      this.textureWidth = 32;
      this.textureHeight = 32;
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.addBox(-3.0F, 0.0F, -3.0F, 6, 10, 6);
      this.Body.setRotationPoint(0.0F, 14.0F, 0.0F);
      this.Body.setTextureSize(32, 32);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.Arm1 = new ModelRenderer(this, 0, 17);
      this.Arm1.addBox(-1.0F, 0.0F, -2.0F, 1, 6, 4);
      this.Arm1.setRotationPoint(-3.0F, 16.0F, 0.0F);
      this.Arm1.setTextureSize(32, 32);
      this.Arm1.mirror = true;
      this.setRotation(this.Arm1, 0.0F, 0.0F, 0.0F);
      this.Arm2 = new ModelRenderer(this, 11, 17);
      this.Arm2.addBox(0.0F, 0.0F, -2.0F, 1, 6, 4);
      this.Arm2.setRotationPoint(3.0F, 16.0F, 0.0F);
      this.Arm2.setTextureSize(32, 32);
      this.Arm2.mirror = true;
      this.setRotation(this.Arm2, 0.0F, 0.0F, 0.0F);
      this.Beak = new ModelRenderer(this, 0, 28);
      this.Beak.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Beak.setRotationPoint(0.0F, 17.0F, -5.0F);
      this.Beak.setTextureSize(32, 32);
      this.Beak.mirror = true;
      this.setRotation(this.Beak, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Body.render(f5);
      this.Arm1.render(f5);
      this.Arm2.render(f5);
      this.Beak.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.Arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
   }
}
