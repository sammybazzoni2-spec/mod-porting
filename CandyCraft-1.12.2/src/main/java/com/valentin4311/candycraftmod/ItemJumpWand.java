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

public class ItemJumpWand extends Item {
   public ItemJumpWand() {
      this.setMaxStackSize(1);
      this.setMaxDamage(10);
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 720000;
   }

   @Override
   public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
      if (living instanceof EntityPlayer && stack.getItemDamage() == 1) {
         EntityPlayer player = (EntityPlayer)living;
         double d0 = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI));
         double d1 = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI));
         double d2 = (double)(-MathHelper.sin(player.rotationPitch / 180.0F * (float)Math.PI) * 2.0F);
         if (d1 == 0.0D && d0 == 0.0D) {
            player.motionY /= 2.0D;
            player.motionY += 2.0D;
         } else {
            this.knockBack(player, 4.0F, -d0, -d1, d2);
         }
         player.velocityChanged = true;
         stack.setItemDamage(0);
      }
   }

   public void knockBack(Entity entity, float power, double x, double z, double y) {
      if (y < 0.1D) {
         y = 0.1D;
      }
      entity.isAirBorne = true;
      float f1 = MathHelper.sqrt(x * x + z * z);
      entity.motionX /= 2.0D;
      entity.motionY /= 2.0D;
      entity.motionZ /= 2.0D;
      entity.motionX -= x / (double)f1 * (double)power;
      entity.motionY += y;
      entity.motionZ -= z / (double)f1 * (double)power;
   }

   @SideOnly(Side.CLIENT)
   public void renderBrokenItemStack(ItemStack stack, EntityPlayer player) {
      for (int i = 0; i < 10; ++i) {
         Vec3d v = new Vec3d(((double)itemRand.nextFloat() - 0.5D) * 0.3D, (double)(-itemRand.nextFloat()) * 0.6D - 0.3D, 0.6D);
         v = v.rotatePitch(-player.rotationPitch * (float)Math.PI / 180.0F);
         v = v.rotateYaw(-player.rotationYaw * (float)Math.PI / 180.0F);
         v = v.addVector(player.posX, player.posY + (double)player.getEyeHeight() + 0.5D, player.posZ);
         player.world.spawnParticle(EnumParticleTypes.REDSTONE, v.x, v.y, v.z, 0.3D, 0.3D, 1.0D);
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
