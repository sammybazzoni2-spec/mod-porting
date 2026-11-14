package com.valentin4311.candycraftmod;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGingerBreadMan extends EntityVillager implements IMerchant, INpc {
   public final String[] jobs = new String[]{"Blacksmith", "Farmer", "Citizen", "Elder"};
   private MerchantRecipeList buyingList = new MerchantRecipeList();
   EntityAIAvoidPlayerGinger ai;

   public EntityGingerBreadMan(World world) {
      super(world);
      this.setSize(0.3F, 0.9F);
   }

   @Override
   protected void initEntityAI() {
      ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
      ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
      this.ai = new EntityAIAvoidPlayerGinger(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAvoidEntity<EntityZombie>(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
      this.tasks.addTask(1, new EntityAITradePlayer(this));
      this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
      this.tasks.addTask(6, new EntityAIVillagerMate(this));
      this.tasks.addTask(8, new EntityAIPlay(this, 0.32D));
      this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
      this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityGingerBreadMan.class, 5.0F, 0.02F));
      this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.8D));
      this.tasks.addTask(3, new EntityAILookIdle(this));
      this.tasks.addTask(4, this.ai);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compound) {
      super.writeEntityToNBT(compound);
      if (this.buyingList != null) {
         compound.setTag("GingerOffers", this.buyingList.getRecipiesAsTags());
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("GingerOffers", 10)) {
         this.buyingList = new MerchantRecipeList(compound.getCompoundTag("GingerOffers"));
      }
   }

   @Override
   protected void updateAITasks() {
      super.updateAITasks();
      if (this.getProfession() != 3) {
         if (this.getMoveHelper().isUpdating()) {
            double speed = this.getMoveHelper().getSpeed();
            if (speed == 0.6D) {
               this.setSneaking(true);
               this.setSprinting(false);
            } else if (speed == 1.33D) {
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
   }

   @Override
   public void setProfession(int profession) {
      if (profession == 3) {
         if (this.ai != null) {
            this.tasks.removeTask(this.ai);
         }
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.83D);
         this.buyingList.clear();
         this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.PEZ, 5), new ItemStack(CandyCraft.WhiteKey)));
         this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.PEZ, 10), new ItemStack(CandyCraft.SkyEmblem)));
         this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.PEZ, 20), new ItemStack(CandyCraft.CD3)));
      }
      super.setProfession(profession);
   }

   @Override
   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
      int i = this.rand.nextInt(this.jobs.length - 1);
      this.setProfession(i);
      if (this.getProfession() == 0) {
         if (this.rand.nextInt(3) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 4), new ItemStack(CandyCraft.HoneyArrow, this.rand.nextInt(4) + 1)));
         }

         if (this.rand.nextInt(5) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, 64), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 32), new ItemStack(CandyCraft.CaramelBow, 1)));
         }

         if (this.rand.nextInt(4) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 50), new ItemStack(CandyCraft.HoneySword, 1)));
         }

         if (this.rand.nextInt(3) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 40), new ItemStack(CandyCraft.HoneyHelmet, 1)));
         }

         if (this.rand.nextInt(6) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(8) + 55), new ItemStack(CandyCraft.HoneyPlate, 1)));
         }

         if (this.rand.nextInt(4) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(8) + 50), new ItemStack(CandyCraft.HoneyLeggings, 1)));
         }

         if (this.rand.nextInt(3) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(15) + 35), new ItemStack(CandyCraft.HoneyBoots, 1)));
         }

         if (this.rand.nextInt(3) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 15), new ItemStack(CandyCraft.HoneyComb, 1)));
         }

         if (this.rand.nextInt(3) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 35), new ItemStack(CandyCraft.PEZ, 1)));
         }

         if (this.buyingList.size() == 0) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 4), new ItemStack(CandyCraft.HoneyArrow, this.rand.nextInt(4) + 1)));
         }
      } else if (this.getProfession() == 1) {
         if (this.rand.nextInt(3) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 40), new ItemStack(CandyCraft.Fork, this.rand.nextInt(2) + 1)));
         }

         if (this.rand.nextInt(3) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 24), new ItemStack(CandyCraft.LollipopSeeds, this.rand.nextInt(2) + 1)));
         }

         this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 2), new ItemStack(CandyCraft.Dragibus, this.rand.nextInt(2) + 1)));
         if (this.rand.nextInt(3) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 4), new ItemStack(CandyCraft.PinkAlgae, this.rand.nextInt(4) + 1)));
         }

         if (this.rand.nextInt(5) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(12) + 4), new ItemStack(CandyCraft.GreenAlgae, this.rand.nextInt(6) + 1)));
         }

         if (this.rand.nextInt(5) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(12) + 4), new ItemStack(CandyCraft.BananaSeaweeds, this.rand.nextInt(6) + 1)));
         }

         if (this.rand.nextInt(8) == 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 15), new ItemStack(CandyCraft.LollipopBlock, this.rand.nextInt(1) + 1)));
         }

         if (this.rand.nextInt(3) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 8), new ItemStack(CandyCraft.Lollipop, this.rand.nextInt(8) + 3)));
         }

         if (this.buyingList.size() == 0) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(10) + 24), new ItemStack(CandyCraft.LollipopSeeds, this.rand.nextInt(2) + 1)));
         }
      } else if (this.getProfession() == 2) {
         if (this.rand.nextInt(4) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.Waffle, this.rand.nextInt(6) + 5), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(15) + 5)));
         }

         if (this.rand.nextInt(3) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.BarleySugar, this.rand.nextInt(6) + 5), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(15) + 5)));
         }

         if (this.rand.nextInt(3) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.Licorice, this.rand.nextInt(6) + 5), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 5)));
         }

         if (this.rand.nextInt(5) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.HoneyShard, this.rand.nextInt(15) + 30), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 5)));
         }

         if (this.rand.nextInt(5) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.JellyJump, this.rand.nextInt(5) + 10), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 5)));
         }

         if (this.rand.nextInt(5) < 1) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.CaramelBlock, this.rand.nextInt(5) + 10), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 5)));
         }

         if (this.rand.nextInt(5) < 3) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.HoneyArrow, this.rand.nextInt(5) + 20), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 5)));
         }

         if (this.rand.nextInt(5) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.CandyLog, this.rand.nextInt(5) + 20), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(20) + 5)));
         }

         if (this.rand.nextInt(10) < 2) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.ChocolateCoins, 64), new ItemStack(CandyCraft.ChocolateCoins, 64), new ItemStack(CandyCraft.GingerbreadEmblem, 1)));
         }

         if (this.buyingList.size() == 0) {
            this.buyingList.add(new MerchantRecipe(new ItemStack(CandyCraft.BarleySugar, this.rand.nextInt(6) + 5), new ItemStack(CandyCraft.ChocolateCoins, this.rand.nextInt(15) + 5)));
         }
      }

      return data;
   }

   @Override
   public boolean processInteract(EntityPlayer player, EnumHand hand) {
      ItemStack held = player.getHeldItem(hand);
      if (held.getItem() == Items.NAME_TAG) {
         held.interactWithEntity(player, this, hand);
         return true;
      }
      if (this.isEntityAlive() && !this.isTrading() && !this.isChild()) {
         if (!this.world.isRemote && !this.buyingList.isEmpty()) {
            this.setCustomer(player);
            player.displayVillagerTradeGui(this);
         }
         return true;
      }
      return false;
   }

   @Override
   public MerchantRecipeList getRecipes(EntityPlayer player) {
      return this.buyingList;
   }

   @Override
   public void setRecipes(MerchantRecipeList list) {
   }

   @Override
   public void useRecipe(MerchantRecipe recipe) {
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33D);
   }

   @Override
   public EntityVillager createChild(EntityAgeable ageable) {
      EntityGingerBreadMan child = new EntityGingerBreadMan(this.world);
      child.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(this)), (IEntityLivingData)null);
      return child;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return this.isTrading() ? SoundEvents.ENTITY_VILLAGER_TRADING : null;
   }

   @Override
   protected float getSoundPitch() {
      return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + (this.isChild() ? 1.5F : 0.2F);
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_VILLAGER_HURT;
   }

   @Override
   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_VILLAGER_DEATH;
   }
}
