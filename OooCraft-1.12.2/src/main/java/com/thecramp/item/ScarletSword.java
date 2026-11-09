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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ScarletSword extends ItemSword {
   public ScarletSword(Item.ToolMaterial material) {
      super(material);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!world.isRemote) {
         player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 1));
         player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5, 1));
         player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 1));
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 1));
         player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 5, 1));
         player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 1));
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
   }

   @Override
   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 10));
      return true;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("RIGHT-CLICK: Hasty Recovery");
      tooltip.add("SPECIAL ATTACK: Slowing Strike");
   }
}
