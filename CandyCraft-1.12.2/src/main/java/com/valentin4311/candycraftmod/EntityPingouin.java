package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityPingouin extends EntityAnimal {
   private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityPingouin.class, DataSerializers.VARINT);
   private static final DataParameter<Integer> SUPER = EntityDataManager.<Integer>createKey(EntityPingouin.class, DataSerializers.VARINT);

   public EntityPingouin(World world) {
      super(world);
      this.setSize(0.6F, 1.0F);
   }

   @Override
   protected void initEntityAI() {
      ((PathNavigateGround)this.getNavigator()).setCanSwim(false);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
      this.tasks.addTask(2, new EntityAIMate(this, 0.5D));
      this.tasks.addTask(3, new EntityAITempt(this, 0.5D, CandyCraft.CranberryFish, false));
      this.tasks.addTask(3, new EntityAITempt(this, 0.5D, CandyCraft.MarshmallowFlower, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 0.9D));
      this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.5D));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.onGround && this.motionY < 0.0D) {
         this.motionY *= 0.6D;
      }
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack held = player.getHeldItem(hand);
      if (!this.world.isRemote && !held.isEmpty() && held.getItem() == CandyCraft.MarshmallowFlower) {
         this.entityDropItem(new ItemStack(CandyCraft.IceCream, this.rand.nextInt(6) + 5, this.getColor()), 0.5F);
         if (!player.capabilities.isCreativeMode) {
            held.shrink(1);
         }
      }
      return super.processInteract(player, hand);
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
   }

   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return !stack.isEmpty() && stack.getItem() == CandyCraft.CranberryFish;
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(COLOR, 0);
      this.dataManager.register(SUPER, 0);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Super", this.isSuper());
      compound.setInteger("Color", this.getColor());
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      this.setSuper(compound.getBoolean("Super"));
      this.setColor(compound.getInteger("Color"));
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      data = super.onInitialSpawn(difficulty, data);
      this.setColor(this.rand.nextInt(3));
      if (this.rand.nextInt(30) == 0) {
         this.setSuper(true);
      }
      return data;
   }

   public void setColor(int i) {
      this.dataManager.set(COLOR, i);
   }

   public int getColor() {
      return this.dataManager.get(COLOR) & 3;
   }

   public void setSuper(boolean i) {
      this.dataManager.set(SUPER, i ? 1 : 0);
   }

   public boolean isSuper() {
      return this.dataManager.get(SUPER) == 1;
   }

   @Override
   public EntityAgeable createChild(EntityAgeable ageable) {
      return new EntityPingouin(this.world);
   }
}
