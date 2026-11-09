package com.thecramp.item;

import com.thecramp.lib.ModSounds;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HoloPlayer extends Item {
   public HoloPlayer() {
      this.maxStackSize = 1;
      this.setMaxDamage(5);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!world.isRemote) {
         player.sendMessage(new TextComponentTranslation("msg.test.txt"));
         world.playSound(null, player.posX, player.posY, player.posZ, ModSounds.HOLO_MESSAGE, SoundCategory.PLAYERS, 1.0F, 1.0F);
         player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 780, 3));
         player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 780, 3));
         player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 780, 3));
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 780, 3));
         player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 780, 3));
      }
      // The original consumed the held item entirely.
      player.setHeldItem(hand, ItemStack.EMPTY);
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("RIGHT-CLICK: Play Message");
   }
}
