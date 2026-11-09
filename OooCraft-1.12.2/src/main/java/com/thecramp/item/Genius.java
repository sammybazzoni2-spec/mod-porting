package com.thecramp.item;

import com.thecramp.lib.ModSounds;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Genius extends Item {
   public Genius() {
      this.maxStackSize = 1;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!world.isRemote) {
         world.playSound(null, player.posX, player.posY, player.posZ, ModSounds.GENIUS, SoundCategory.PLAYERS, 1.0F, 1.0F);
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("RIGHT-CLICK: Annoy Your Friends!");
   }
}
