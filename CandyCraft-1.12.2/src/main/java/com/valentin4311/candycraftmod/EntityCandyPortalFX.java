package com.valentin4311.candycraftmod;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityCandyPortalFX extends Particle {
   private float portalParticleScale;
   private double portalPosX;
   private double portalPosY;
   private double portalPosZ;

   public EntityCandyPortalFX(World world, double x, double y, double z, double mx, double my, double mz) {
      super(world, x, y, z, mx, my, mz);
      this.motionX = mx;
      this.motionY = my;
      this.motionZ = mz;
      this.posX = x;
      this.posY = y;
      this.posZ = z;
      this.portalPosX = x;
      this.portalPosY = y;
      this.portalPosZ = z;
      this.setPosition(x, y, z);
      this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
      this.portalParticleScale = this.particleScale;
      this.particleRed = 1.0F;
      this.particleGreen = 0.3F;
      this.particleBlue = 0.0F;
      this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
      this.canCollide = false;
      this.setParticleTextureIndex((int)(Math.random() * 8.0D));
   }

   @Override
   public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      float f6 = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
      f6 = 1.0F - f6;
      f6 *= f6;
      f6 = 1.0F - f6;
      this.particleScale = this.portalParticleScale * f6;
      super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
   }

   @Override
   public int getBrightnessForRender(float partialTick) {
      int i = super.getBrightnessForRender(partialTick);
      float f1 = (float)this.particleAge / (float)this.particleMaxAge;
      f1 *= f1;
      f1 *= f1;
      int j = i & 255;
      int k = i >> 16 & 255;
      k += (int)(f1 * 15.0F * 16.0F);
      if (k > 240) {
         k = 240;
      }
      return j | k << 16;
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      float f = (float)this.particleAge / (float)this.particleMaxAge;
      float f1 = f;
      f = -f + f * f * 2.0F;
      f = 1.0F - f;
      this.posX = this.portalPosX + this.motionX * (double)f;
      this.posY = this.portalPosY + this.motionY * (double)f + (double)(1.0F - f1);
      this.posZ = this.portalPosZ + this.motionZ * (double)f;
      this.setPosition(this.posX, this.posY, this.posZ);
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }
   }
}
