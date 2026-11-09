package com.thecramp.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityJamesBaxter extends EntityAnimal {
   public EntityJamesBaxter(World world) {
      super(world);
      this.isImmuneToFire = false;
      // TODO(port): 1.7.10 called getNavigator().setAvoidsWater(true); no direct 1.12 equivalent used here.
      this.setSize(0.5F, 1.7F);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return SoundEvents.ENTITY_HORSE_AMBIENT;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_HORSE_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_HORSE_DEATH;
   }

   @Override
   public EntityAgeable createChild(EntityAgeable ageable) {
      return null;
   }
}
