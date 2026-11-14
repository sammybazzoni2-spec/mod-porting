package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class EntityAICandyArrow extends EntityAIBase {
   private final EntityLiving entityHost;
   private final IRangedAttackMob rangedAttackEntityHost;
   public EntityLivingBase attackTarget;
   private int rangedAttackTime;
   private double entityMoveSpeed;
   private int seeTime;
   private int minRangedAttackTime;
   private int maxRangedAttackTime;
   private float attackRange;
   private float maxAttackDistance;

   public EntityAICandyArrow(IRangedAttackMob attacker, double speed, int attackTime, float range) {
      this(attacker, speed, attackTime, attackTime, range);
   }

   public EntityAICandyArrow(IRangedAttackMob attacker, double speed, int minTime, int maxTime, float range) {
      this.rangedAttackTime = -1;
      if (!(attacker instanceof EntityLivingBase)) {
         throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
      } else {
         this.rangedAttackEntityHost = attacker;
         this.entityHost = (EntityLiving)attacker;
         this.entityMoveSpeed = speed;
         this.minRangedAttackTime = minTime;
         this.maxRangedAttackTime = maxTime;
         this.attackRange = range;
         this.maxAttackDistance = range * range;
         this.setMutexBits(3);
      }
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase target = this.entityHost.getAttackTarget();
      if (target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.disableDamage) {
         return false;
      } else if (target == null) {
         return false;
      } else {
         this.attackTarget = target;
         return true;
      }
   }

   @Override
   public boolean shouldContinueExecuting() {
      return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
   }

   @Override
   public void resetTask() {
      this.attackTarget = null;
      this.seeTime = 0;
      this.rangedAttackTime = -1;
   }

   @Override
   public void updateTask() {
      double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
      boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);
      if (flag) {
         ++this.seeTime;
      } else {
         this.seeTime = 0;
      }

      if (d0 <= (double)this.maxAttackDistance + 20.0D && this.seeTime >= 20) {
         this.entityHost.getNavigator().clearPath();
      } else {
         this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
      }

      this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
      if (--this.rangedAttackTime == 0) {
         if (d0 > (double)this.maxAttackDistance + 20.0D || !flag) {
            return;
         }

         float f = MathHelper.sqrt(d0) / this.attackRange;
         float f1 = f;
         if (f < 0.1F) {
            f1 = 0.1F;
         }

         if (f1 > 1.0F) {
            f1 = 1.0F;
         }

         this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, f1);
         this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.minRangedAttackTime) + (float)this.minRangedAttackTime);
      } else if (this.rangedAttackTime < 0) {
         float f = MathHelper.sqrt(d0) / this.attackRange;
         this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.minRangedAttackTime) + (float)this.minRangedAttackTime);
      }
   }
}
