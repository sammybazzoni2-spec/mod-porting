package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMageSuguard extends EntitySuguard {
   private boolean wait = true;
   private boolean spawned = false;
   private int countDown = 0;

   public EntityMageSuguard(World world) {
      super(world);
      this.isAngry = true;
   }

   @Override
   protected boolean canTargetPlayers() {
      return true;
   }

   @Override
   public boolean attackEntityAsMob(Entity target) {
      target.setFire(6);
      return super.attackEntityAsMob(target);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
   }

   @Override
   protected boolean canTriggerWalking() {
      return this.isWaiting();
   }

   @Override
   public void onLivingUpdate() {
      if (this.getHeldItemMainhand().isEmpty()) {
         this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CandyCraft.JumpWand));
      }
      if (this.isWaiting()) {
         this.getNavigator().clearPath();
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
      } else {
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
      }
      if (!this.world.isRemote && this.countDown > 0) {
         --this.countDown;
         if (this.countDown <= 0) {
            this.setSpawned(false);
         }
      }
      if (!this.world.isRemote && this.world.getClosestPlayerToEntity(this, 8.0D) != null) {
         this.setWaiting(false);
         if (!this.isSpawned()) {
            for (int i = 0; i <= 8; ++i) {
               double d1 = (double)(-MathHelper.sin((float)i / 3.75F * (float)Math.PI) * 2.5F) + this.posX;
               double d2 = (double)(MathHelper.cos((float)i / 3.75F * (float)Math.PI) * 2.5F) + this.posZ;
               double d3 = this.posY + 2.0D;
               EntitySuguard suguard = new EntitySuguard(this.world);
               suguard.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CandyCraft.Dynamite));
               suguard.isAngry = true;
               suguard.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
               suguard.setHealth(10.0F);
               if (this.world.isAirBlock(new BlockPos((int)d1, (int)d3, (int)d2))) {
                  suguard.setPosition(d1, d3, d2);
               } else {
                  suguard.setPosition(this.posX, this.posY, this.posZ);
               }
               this.world.spawnEntity(suguard);
            }
            this.setSpawned(true);
            this.countDown = 2400;
         }
      } else {
         this.setWaiting(true);
      }
      super.onLivingUpdate();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Waiting", this.isWaiting());
      compound.setInteger("countDown", this.countDown);
      compound.setBoolean("Spawned", this.isSpawned());
   }

   private boolean isWaiting() {
      return this.wait;
   }

   private void setWaiting(boolean waiting) {
      this.wait = waiting;
   }

   public boolean isSpawned() {
      return this.spawned;
   }

   private void setSpawned(boolean spawn) {
      this.spawned = spawn;
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setWaiting(compound.getBoolean("Waiting"));
      this.countDown = compound.getInteger("countDown");
      this.setSpawned(compound.getBoolean("Spawned"));
   }

   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
      ItemStack stack = new ItemStack(CandyCraft.GrenadineCandy);
      int i = this.getRandomPotion();
      stack.setItemDamage(i);
      stack.setTagCompound(new NBTTagCompound());
      stack.getTagCompound().setInteger("MetaSystem", i);
      this.entityDropItem(stack, 0.0F);
   }

   public int getRandomPotion() {
      String finalResult = "";
      for (int i = 0; i < 4; ++i) {
         String id = String.valueOf(this.rand.nextInt(22) + 1);
         finalResult = finalResult + (id.length() == 1 ? "0" + id : id);
      }
      return Integer.valueOf(finalResult);
   }
}
