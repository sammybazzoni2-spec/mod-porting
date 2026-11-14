package com.valentin4311.candycraftmod;

import javax.annotation.Nullable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemCandyBow extends Item {
   public ItemCandyBow() {
      this.maxStackSize = 1;
      this.setMaxDamage(100);
      this.addPropertyOverride(new ResourceLocation("candycraftmod", "pull"), new IItemPropertyGetter() {
         public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
            if (entity == null || !entity.isHandActive() || entity.getActiveItemStack() != stack) {
               return 0.0F;
            }
            return (float)(stack.getMaxItemUseDuration() - entity.getItemInUseCount());
         }
      });
   }

   @Override
   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
      return !repair.isEmpty() && repair.getItem() == Item.getItemFromBlock(CandyCraft.CaramelBlock);
   }

   private boolean hasAmmo(EntityPlayer player) {
      Item ammo = CandyCraft.HoneyArrow;
      for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
         ItemStack s = player.inventory.getStackInSlot(i);
         if (!s.isEmpty() && s.getItem() == ammo) {
            return true;
         }
      }
      return false;
   }

   private void consumeAmmo(EntityPlayer player) {
      Item ammo = CandyCraft.HoneyArrow;
      for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
         ItemStack s = player.inventory.getStackInSlot(i);
         if (!s.isEmpty() && s.getItem() == ammo) {
            s.shrink(1);
            if (s.isEmpty()) {
               player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
            }
            return;
         }
      }
   }

   @Override
   public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
      if (!(living instanceof EntityPlayer)) {
         return;
      }
      EntityPlayer player = (EntityPlayer)living;
      int j = this.getMaxItemUseDuration(stack) - timeLeft;
      boolean infinite = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
      if (infinite || this.hasAmmo(player)) {
         float f = (float)j / 20.0F;
         f = (f * f + f * 4.0F) / 3.0F;
         if ((double)f < 0.1D) {
            return;
         }
         if (f > 1.0F) {
            f = 1.0F;
         }
         EntityCandyArrow entityarrow = new EntityCandyArrow(world, player, f * 2.0F);
         if (f == 1.0F) {
            entityarrow.setIsCritical(true);
         }
         int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
         if (k > 0) {
            entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
         }
         int l = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
         if (l > 0) {
            entityarrow.setKnockbackStrength(l);
         }
         int l2 = EnchantmentHelper.getEnchantmentLevel(CandyCraft.honeyGlue, stack);
         if (l2 > 0) {
            entityarrow.slow = l2;
         }
         if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
            entityarrow.setFire(100);
         }
         stack.damageItem(1, player);
         world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
         if (infinite) {
            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
         } else {
            this.consumeAmmo(player);
            entityarrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
         }
         if (!world.isRemote) {
            world.spawnEntity(entityarrow);
         }
      }
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 72000;
   }

   @Override
   public EnumAction getItemUseAction(ItemStack stack) {
      return EnumAction.BOW;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (player.capabilities.isCreativeMode || this.hasAmmo(player)) {
         player.setActiveHand(hand);
         return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
      }
      return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
   }

   @Override
   public int getItemEnchantability() {
      return 1;
   }
}
