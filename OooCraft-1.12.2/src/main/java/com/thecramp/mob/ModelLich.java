package com.thecramp.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLich extends ModelBase {
   ModelRenderer head;
   ModelRenderer crown;
   ModelRenderer righthorn;
   ModelRenderer lefthorn;
   ModelRenderer upperbody;
   ModelRenderer lowerbody;
   ModelRenderer upperarmr;
   ModelRenderer upperarml;
   ModelRenderer cape;

   public ModelLich() {
      this.textureWidth = 128;
      this.textureHeight = 128;
      this.head = new ModelRenderer(this, 0, 63);
      this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
      this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.head.setTextureSize(64, 32);
      this.head.mirror = true;
      this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
      this.crown = new ModelRenderer(this, 0, 41);
      this.crown.addBox(-2.0F, -1.0F, -2.0F, 4, 1, 4);
      this.crown.setRotationPoint(0.0F, -8.0F, 0.0F);
      this.crown.setTextureSize(64, 32);
      this.crown.mirror = true;
      this.setRotation(this.crown, 0.0F, 0.0F, 0.0F);
      this.righthorn = new ModelRenderer(this, 0, 55);
      this.righthorn.addBox(-1.0F, -4.533333F, -1.0F, 2, 4, 2);
      this.righthorn.setRotationPoint(-4.0F, -7.5F, 0.0F);
      this.righthorn.setTextureSize(64, 32);
      this.righthorn.mirror = true;
      this.setRotation(this.righthorn, 0.0F, 0.0F, 0.0F);
      this.lefthorn = new ModelRenderer(this, 0, 47);
      this.lefthorn.addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2);
      this.lefthorn.setRotationPoint(4.0F, -8.5F, 0.0F);
      this.lefthorn.setTextureSize(64, 32);
      this.lefthorn.mirror = true;
      this.setRotation(this.lefthorn, 0.0F, 0.0F, 0.0F);
      this.upperbody = new ModelRenderer(this, 0, 83);
      this.upperbody.addBox(-7.0F, 0.0F, -7.0F, 14, 9, 14);
      this.upperbody.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.upperbody.setTextureSize(64, 32);
      this.upperbody.mirror = true;
      this.setRotation(this.upperbody, 0.0F, 0.0F, 0.0F);
      this.lowerbody = new ModelRenderer(this, 0, 0);
      this.lowerbody.addBox(-10.0F, 0.0F, -7.0F, 20, 13, 14);
      this.lowerbody.setRotationPoint(0.0F, 9.0F, 0.0F);
      this.lowerbody.setTextureSize(64, 32);
      this.lowerbody.mirror = true;
      this.setRotation(this.lowerbody, 0.0F, 0.0F, 0.0F);
      this.upperarmr = new ModelRenderer(this, 71, 0);
      this.upperarmr.addBox(-2.0F, 0.0F, -1.0F, 2, 7, 2);
      this.upperarmr.setRotationPoint(-7.0F, 1.0F, -4.0F);
      this.upperarmr.setTextureSize(64, 32);
      this.upperarmr.mirror = true;
      this.setRotation(this.upperarmr, 0.0F, 0.0F, 0.0F);
      this.upperarml = new ModelRenderer(this, 81, 0);
      this.upperarml.addBox(0.0F, 0.0F, -1.0F, 2, 7, 2);
      this.upperarml.setRotationPoint(7.0F, 1.0F, -4.0F);
      this.upperarml.setTextureSize(64, 32);
      this.upperarml.mirror = true;
      this.setRotation(this.upperarml, 0.0F, 0.0F, 0.0F);
      this.cape = new ModelRenderer(this, 83, 40);
      this.cape.addBox(-7.0F, 0.0F, 0.0F, 14, 24, 0);
      this.cape.setRotationPoint(0.0F, 0.0F, 7.0F);
      this.cape.setTextureSize(64, 32);
      this.cape.mirror = true;
      this.setRotation(this.cape, 0.9491906F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head.render(f5);
      this.crown.render(f5);
      this.righthorn.render(f5);
      this.lefthorn.render(f5);
      this.upperbody.render(f5);
      this.lowerbody.render(f5);
      this.upperarmr.render(f5);
      this.upperarml.render(f5);
      this.cape.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.upperarmr.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.upperarml.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
   }
}
