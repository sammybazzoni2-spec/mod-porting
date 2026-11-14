package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemJellyWand extends Item {
   public ItemJellyWand() {
      this.setMaxStackSize(1);
      this.setMaxDamage(14);
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 720000;
   }

   @Override
   public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
      if (living instanceof EntityPlayer && stack.getItemDamage() == 1) {
         EntityPlayer player = (EntityPlayer)living;
         for (int i = 0; i < 8; ++i) {
            world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
               world.spawnEntity(new EntityGummyBall(world, player, 1));
            }
         }
         stack.setItemDamage(0);
      }
   }

   @SideOnly(Side.CLIENT)
   public void renderBrokenItemStack(ItemStack stack, EntityPlayer player) {
      for (int i = 0; i < 10; ++i) {
         Vec3d v = new Vec3d(((double)itemRand.nextFloat() - 0.5D) * 0.3D, (double)(-itemRand.nextFloat()) * 0.6D - 0.3D, 0.6D);
         v = v.rotatePitch(-player.rotationPitch * (float)Math.PI / 180.0F);
         v = v.rotateYaw(-player.rotationYaw * (float)Math.PI / 180.0F);
         v = v.addVector(player.posX, player.posY + (double)player.getEyeHeight() + 0.5D, player.posZ);
         player.world.spawnParticle(EnumParticleTypes.REDSTONE, v.x, v.y, v.z, 1.0D, 0.5D, 0.5D);
      }
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!player.capabilities.isCreativeMode) {
         if (stack.getItemDamage() == 0) {
            stack.setItemDamage(stack.getMaxDamage() - 1);
         } else if (stack.getItemDamage() != 1) {
            stack.setItemDamage(stack.getItemDamage() - 1);
         }
         player.setActiveHand(hand);
         if (world.isRemote) {
            this.renderBrokenItemStack(stack, player);
         }
      } else if (!world.isRemote) {
         TextComponentTranslation msg = new TextComponentTranslation("chat.NoCreative");
         msg.getStyle().setColor(TextFormatting.GOLD);
         player.sendMessage(msg);
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
   }
}
