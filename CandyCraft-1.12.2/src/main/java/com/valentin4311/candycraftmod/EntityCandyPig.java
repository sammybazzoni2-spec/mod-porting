package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCandyPig extends EntityPig {
   public EntityCandyPig(World world) {
      super(world);
      this.setSize(0.9F, 0.9F);
   }

   @Override
   protected void initEntityAI() {
      super.initEntityAI();
      float speed = 0.25F;
      this.tasks.taskEntries.clear();
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 0.38D));
      this.tasks.addTask(2, new EntityAIControlledByPlayer(this, 0.34F));
      this.tasks.addTask(3, new EntityAIMate(this, (double)speed));
      this.tasks.addTask(4, new EntityAITempt(this, 1.0D, CandyCraft.DragibusStick, false));
      this.tasks.addTask(4, new EntityAITempt(this, 1.0D, CandyCraft.Dragibus, false));
      this.tasks.addTask(5, new EntityAIFollowParent(this, 0.28D));
      this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, (double)speed));
      this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(8, new EntityAILookIdle(this));
      this.tasks.addTask(4, new EntityAIAvoidPlayerGinger(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
   }

   @Override
   public boolean canBeSteered() {
      if (!(this.getControllingPassenger() instanceof EntityPlayer)) {
         return false;
      }
      EntityPlayer player = (EntityPlayer)this.getControllingPassenger();
      ItemStack main = player.getHeldItemMainhand();
      ItemStack off = player.getHeldItemOffhand();
      return (!main.isEmpty() && main.getItem() == CandyCraft.DragibusStick) || (!off.isEmpty() && off.getItem() == CandyCraft.DragibusStick);
   }

   @Override
   protected void updateAITasks() {
      super.updateAITasks();
      if (this.getMoveHelper().isUpdating()) {
         double d0 = this.getMoveHelper().getSpeed();
         if (d0 == 0.6D) {
            this.setSneaking(true);
            this.setSprinting(false);
         } else if (d0 == 1.33D) {
            this.setSneaking(false);
            this.setSprinting(true);
         } else {
            this.setSneaking(false);
            this.setSprinting(false);
         }
      } else {
         this.setSneaking(false);
         this.setSprinting(false);
      }
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem() == CandyCraft.Dragibus;
   }

   @Override
   protected ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      int count = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + lootingModifier);

      for (int i = 0; i < count; ++i) {
         this.dropItem(CandyCraft.BarleySugar, 1);
      }

      if (this.getSaddled()) {
         this.dropItem(Items.SADDLE, 1);
      }
   }

   @Override
   public EntityPig createChild(EntityAgeable ageable) {
      return new EntityCandyPig(this.world);
   }
}
