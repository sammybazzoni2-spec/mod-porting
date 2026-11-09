package com.thecramp.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FinnSword extends ItemSword {
   public FinnSword(Item.ToolMaterial material) {
      super(material);
   }

   private static int i = 0;

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!world.isRemote) {
         if (i >= 0 && i <= 9) {
            player.sendMessage(new TextComponentTranslation("msg.finnsword." + i + ".txt"));
         }
         if (i == 10) {
            i = 0;
         }
         ++i;
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("A Sword Found By Creating a Paradox in Time.");
      tooltip.add("RIGHT-CLICK: Block");
      tooltip.add("SPECIAL ATTACK: None");
   }
}
