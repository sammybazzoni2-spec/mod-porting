package com.valentin4311.candycraftmod;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCandyCreeper extends EntityCreeper {
   private int lastActiveTime;
   private int timeSinceIgnited;
   private int fuseTime = 30;
   private int explosionRadius = 3;
   boolean isExploding = false;
   int counter = 60;
   boolean current = false;

   public EntityCandyCreeper(World world) {
      super(world);
   }

   @Override
   protected void initEntityAI() {
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAICreeperSwell(this));
      this.tasks.addTask(3, new EntityAIAvoidEntity<EntityOcelot>(this, EntityOcelot.class, 6.0F, 0.25D, 0.3D));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 0.3D, false));
      this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.3D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   @Override
   public boolean isPotionApplicable(PotionEffect effect) {
      return effect.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(effect);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
   }

   @Override
   public void onUpdate() {
      if (this.isEntityAlive()) {
         float speed = (float)this.getMoveHelper().getSpeed();
         this.setSprinting(speed != 0.0F);

         this.lastActiveTime = this.timeSinceIgnited;
         int state = this.getCreeperState();
         if (state > 0 && this.timeSinceIgnited == 0) {
            this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
         }

         this.timeSinceIgnited += state;
         if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
         }

         if (this.isExploding) {
            --this.counter;
            this.current = !this.current;
            if (this.current) {
               this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.0F + (float)(60 - this.counter) / 60.0F);
            }

            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw + 1.0F, this.rotationPitch + 1.0F);
            if (!this.world.isRemote && this.counter <= 0) {
               boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
               this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 6), grief);
               this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 6), grief);
               this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 6), grief);
               this.setDead();
               this.dropItem(Items.COOKIE, 32);
            }
         }

         if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            if (!this.world.isRemote) {
               boolean grief = this.world.getGameRules().getBoolean("mobGriefing");
               if (this.getPowered()) {
                  this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)(this.explosionRadius * 2), grief);
               } else {
                  this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius, grief);
               }

               this.setDead();
            }
         }
      }

      super.onUpdate();
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!this.world.isRemote && !stack.isEmpty() && stack.getItem() == CandyCraft.Lollipop && !this.isExploding && this.getHealth() < this.getMaxHealth()) {
         this.isExploding = true;
         this.counter = 60;
         return true;
      } else {
         return super.processInteract(player, hand);
      }
   }

   @Override
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      int count = this.rand.nextInt(3);
      for (int i = 0; i < count; ++i) {
         this.dropItem(Items.COOKIE, 1);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float amount) {
      return this.isExploding ? false : super.attackEntityFrom(source, amount);
   }
}
