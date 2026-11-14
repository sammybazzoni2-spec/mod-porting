package com.valentin4311.candycraftmod;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.Vec3d;

public class EntityAIAvoidPlayerGinger extends EntityAIBase {
   public final Predicate<Entity> canBeSeenSelector = new Predicate<Entity>() {
      public boolean apply(Entity entity) {
         return entity.isEntityAlive() && EntityAIAvoidPlayerGinger.this.theEntity.getEntitySenses().canSee(entity);
      }
   };
   private EntityCreature theEntity;
   private double farSpeed;
   private double nearSpeed;
   private Entity closestLivingEntity;
   private float distanceFromEntity;
   private Path entityPathEntity;
   private PathNavigate entityPathNavigate;
   private Class<? extends Entity> targetEntityClass;

   public EntityAIAvoidPlayerGinger(EntityCreature creature, Class<? extends Entity> targetClass, float distance, double farSpeed, double nearSpeed) {
      this.theEntity = creature;
      this.targetEntityClass = targetClass;
      this.distanceFromEntity = distance;
      this.farSpeed = farSpeed;
      this.nearSpeed = nearSpeed;
      this.entityPathNavigate = creature.getNavigator();
      this.setMutexBits(1);
   }

   @Override
   @SuppressWarnings("unchecked")
   public boolean shouldExecute() {
      if (this.targetEntityClass == EntityPlayer.class) {
         if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed()) {
            return false;
         }

         this.closestLivingEntity = this.theEntity.world.getClosestPlayerToEntity(this.theEntity, (double)this.distanceFromEntity);
         if (this.closestLivingEntity == null) {
            return false;
         }
         if (((EntityPlayer)this.closestLivingEntity).inventory.hasItemStack(new ItemStack(CandyCraft.GingerbreadEmblem))) {
            return false;
         }
      } else {
         List<Entity> list = (List<Entity>)this.theEntity.world.getEntitiesWithinAABB(this.targetEntityClass, this.theEntity.getEntityBoundingBox().grow((double)this.distanceFromEntity, 3.0D, (double)this.distanceFromEntity), this.canBeSeenSelector);
         if (list.isEmpty()) {
            return false;
         }
         this.closestLivingEntity = list.get(0);
      }

      Vec3d vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
      if (vec3 == null) {
         return false;
      } else if (this.closestLivingEntity.getDistanceSq(vec3.x, vec3.y, vec3.z) < this.closestLivingEntity.getDistanceSq(this.theEntity)) {
         return false;
      } else {
         this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.x, vec3.y, vec3.z);
         return this.entityPathEntity != null && this.entityPathEntity.getFinalPathPoint() != null && this.entityPathEntity.getFinalPathPoint().x == net.minecraft.util.math.MathHelper.floor(vec3.x) && this.entityPathEntity.getFinalPathPoint().z == net.minecraft.util.math.MathHelper.floor(vec3.z);
      }
   }

   @Override
   public boolean shouldContinueExecuting() {
      return !this.entityPathNavigate.noPath();
   }

   @Override
   public void startExecuting() {
      this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
   }

   @Override
   public void resetTask() {
      this.closestLivingEntity = null;
   }

   @Override
   public void updateTask() {
      if (this.theEntity.getDistanceSq(this.closestLivingEntity) < 49.0D) {
         this.theEntity.getNavigator().setSpeed(this.nearSpeed);
      } else {
         this.theEntity.getNavigator().setSpeed(this.farSpeed);
      }
   }
}
