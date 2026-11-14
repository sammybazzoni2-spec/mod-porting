package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityCottonCandySpider extends EntitySpider {
   public EntityCottonCandySpider(World world) {
      super(world);
   }

   @Override
   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 48.0F));
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      return data;
   }

   @Override
   protected float getSoundPitch() {
      return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.2F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.6F;
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      boolean hit = super.attackEntityAsMob(target);
      if (hit && target instanceof EntityLivingBase) {
         int seconds = 0;
         int difficulty = this.world.getDifficulty().getId();
         if (difficulty == 2) {
            seconds = 4;
         } else if (difficulty == 3) {
            seconds = 12;
         }
         if (seconds > 0) {
            ((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, seconds * 20, 0));
         }
      }
      return hit;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
   }

   @Override
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      Item item = CandyCraft.CottonCandy;
      if (item != null) {
         int count = this.rand.nextInt(3);
         if (lootingModifier > 0) {
            count += this.rand.nextInt(lootingModifier + 1);
         }
         for (int i = 0; i < count; ++i) {
            this.dropItem(item, 1);
         }
      }
   }
}
