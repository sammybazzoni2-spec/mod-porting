package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNougatGolem extends ModelBase {
   ModelRenderer Cube;

   public ModelNougatGolem() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Cube = new ModelRenderer(this, 0, 0);
      this.Cube.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16);
      this.Cube.setRotationPoint(0.0F, 8.0F, 0.0F);
      this.Cube.setTextureSize(64, 32);
      this.Cube.mirror = true;
      this.setRotation(this.Cube, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Cube.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}

