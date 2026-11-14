package com.valentin4311.candycraftmod;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Metadata only selects the model for renderers: 0 normal, 1 lit, 2 lit/warning
 * (models i85/i85_on/i85_warn and i89/i89_on/i89_warn). In the hand the "candycraftmod:fuse" property is used.
 */
public class ItemDynamite extends Item {
   public Random rand = new Random();

   protected ItemDynamite() {
      this.addPropertyOverride(new ResourceLocation("candycraftmod", "fuse"), new IItemPropertyGetter() {
         public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
            if (entity == null || !entity.isHandActive() || entity.getActiveItemStack() != stack) {
               return 0.0F;
            }
            int i = stack.getMaxItemUseDuration() - entity.getItemInUseCount();
            return i > 60 ? 2.0F : (i > 15 ? 1.0F : 0.0F);
         }
      });
   }

   @Override
   public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
      if (!(living instanceof EntityPlayer)) {
         return;
      }
      EntityPlayer player = (EntityPlayer)living;
      World world = player.world;
      int used = this.getMaxItemUseDuration(stack) - count;
      if (world.isRemote && used > 15) {
         this.renderBrokenItemStack(stack, player);
      }
      if (used == 80) {
         if (!world.isRemote) {
            world.createExplosion((net.minecraft.entity.Entity)null, player.posX, player.posY, player.posZ, 3.0F, world.getGameRules().getBoolean("mobGriefing"));
         }
         stack.shrink(1);
         player.stopActiveHand();
      }
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      player.setActiveHand(hand);
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
   }

   @SideOnly(Side.CLIENT)
   public void renderBrokenItemStack(ItemStack stack, EntityPlayer player) {
      if (this.rand.nextInt(10) == 0) {
         for (int i = 0; i < 2; ++i) {
            Vec3d v = new Vec3d(((double)itemRand.nextFloat() - 0.5D) * 0.3D, (double)(-itemRand.nextFloat()) * 0.6D - 0.3D, 0.6D);
            v = v.rotatePitch(-player.rotationPitch * (float)Math.PI / 180.0F);
            v = v.rotateYaw(-(player.rotationYaw + 25.0F) * (float)Math.PI / 180.0F);
            v = v.addVector(player.posX, player.posY + (double)player.getEyeHeight() + 0.3D, player.posZ);
            player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, v.x, v.y - 0.1D, v.z, 0.0D, 0.1D, 0.0D);
         }
      }
   }

   @Override
   public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
      if (!(living instanceof EntityPlayer)) {
         return;
      }
      EntityPlayer player = (EntityPlayer)living;
      int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
      if (var6 > 15 && var6 < 80) {
         if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
         }
         world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         if (!world.isRemote) {
            if (this == CandyCraft.Dynamite) {
               EntityDynamite dynamite = new EntityDynamite(world, player);
               dynamite.fuse = 80 - var6;
               world.spawnEntity(dynamite);
            } else {
               EntityGlueDynamite dynamite = new EntityGlueDynamite(world, player);
               dynamite.fuse = 80 - var6;
               world.spawnEntity(dynamite);
            }
         }
      }
   }

   @Override
   public int getMaxItemUseDuration(ItemStack stack) {
      return 72000;
   }
}
