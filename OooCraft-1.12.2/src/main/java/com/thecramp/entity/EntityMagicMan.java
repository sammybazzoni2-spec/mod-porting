package com.thecramp.entity;

import com.thecramp.Main.MainRegistry;
import com.thecramp.item.DemonBlood;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagicMan extends EntityMob {
   int q = 0;

   public EntityMagicMan(World world) {
      super(world);
      this.isImmuneToFire = true;
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)100.0F);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue((double)100.0F);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue((double)0.0F);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)8.0F);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.001D);
   }

   protected Item getOooDropItem() {
      return Items.FIREWORKS;
   }

   // 1.7.10 default behaviour of getOooDropItem(): 0-2 items, plus looting bonus.
   @Override
   protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
      Item item = this.getOooDropItem();
      if (item != null) {
         int count = this.rand.nextInt(3);
         if (lootingModifier > 0) {
            count += this.rand.nextInt(lootingModifier + 1);
         }
         for (int k = 0; k < count; ++k) {
            this.dropItem(item, 1);
         }
      }
   }
MagicMan0}
