package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIExplode extends EntityAIBase {
   World worldObj;
   EntityCreature attacker;
   int attackTick;
   double speedTowardsTarget;
   boolean longMemory;
   Path entityPathEntity;
   Class<? extends Entity> classTarget;
   private int delayCounter;
   private double targetX;
   private double targetY;
   private double targetZ;
   private int failedPathFindingPenalty;

   public EntityAIExplode(EntityCreature creature, Class<? extends Entity> targetClass, double speed, boolean longMemory) {
      this(creature, speed, longMemory);
      this.classTarget = targetClass;
   }

   public EntityAIExplode(EntityCreature creature, double speed, boolean longMemory) {
      this.attacker = creature;
      this.worldObj = creature.world;
      this.speedTowardsTarget = speed;
      this.longMemory = longMemory;
      this.setMutexBits(3);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase target = this.attacker.getAttackTarget();
      if (target == null) {
         return false;
      } else if (!target.isEntityAlive()) {
         return false;
      } else if (this.classTarget != null && !this.classTarget.isAssignableFrom(target.getClass())) {
         return false;
      } else if (--this.delayCounter <= 0) {
         this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(target);
         this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
         return this.entityPathEntity != null;
      } else {
         return true;
      }
   }

   @Override
   public boolean shouldContinueExecuting() {
      EntityLivingBase target = this.attacker.getAttackTarget();
      if (target == null || !target.isEntityAlive()) {
         return false;
      }
      if (!this.longMemory) {
         return !this.attacker.getNavigator().noPath();
      }
      return this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(target));
   }

   @Override
   public void startExecuting() {
      this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
      this.delayCounter = 0;
   }

   @Override
   public void resetTask() {
      this.attacker.getNavigator().clearPath();
   }

   @Override
   public void updateTask() {
      EntityLivingBase target = this.attacker.getAttackTarget();
      this.attacker.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
      double d0 = this.attacker.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);
      double d1 = (double)(this.attacker.width * 2.0F * this.attacker.width * 2.0F + target.width);
      --this.delayCounter;
      if ((this.longMemory || this.attacker.getEntitySenses().canSee(target)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || target.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
         this.targetX = target.posX;
         this.targetY = target.getEntityBoundingBox().minY;
         this.targetZ = target.posZ;
         this.delayCounter = this.failedPathFindingPenalty + 4 + this.attacker.getRNG().nextInt(7);
         if (this.attacker.getNavigator().getPath() != null) {
            PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
            if (finalPathPoint != null && target.getDistanceSq((double)finalPathPoint.x, (double)finalPathPoint.y, (double)finalPathPoint.z) < 1.0D) {
               this.failedPathFindingPenalty = 0;
            } else {
               this.failedPathFindingPenalty += 10;
            }
         } else {
            this.failedPathFindingPenalty += 10;
         }

         if (d0 > 1024.0D) {
            this.delayCounter += 10;
         } else if (d0 > 256.0D) {
            this.delayCounter += 5;
         }

         if (!this.attacker.getNavigator().tryMoveToEntityLiving(target, this.speedTowardsTarget)) {
            this.delayCounter += 15;
         }
      }

      this.attackTick = Math.max(this.attackTick - 1, 0);
      if (d0 <= d1 && this.attackTick <= 20 && ((EntityNougatGolem)this.attacker).isBase()) {
         boolean grief = this.worldObj.getGameRules().getBoolean("mobGriefing");
         this.attackTick = 20;
         EntityNougatGolem last = (EntityNougatGolem)this.attacker;
         this.attacker.world.createExplosion(this.attacker, last.posX, last.posY, last.posZ, 2.0F, grief);

         while (!last.isTop()) {
            if (last != this.attacker) {
               this.attacker.world.createExplosion(this.attacker, last.posX, last.posY, last.posZ, 2.0F, grief);
            }

            if (last.getPassengers().isEmpty() || !(last.getPassengers().get(0) instanceof EntityNougatGolem)) {
               break;
            }

            last = (EntityNougatGolem)last.getPassengers().get(0);
         }

         last.setDead();
      }
   }
}
