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

public class GrassSwordSword extends ItemSword {
   public GrassSwordSword(Item.ToolMaterial material) {
      super(material);
   }

   @Override
   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      target.addPotionEffect(new PotionEffect(MobEffects.POISON, 50, 5));
      return true;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("RIGHT-CLICK: Block");
      tooltip.add("SPECIAL ATTACK: Wrath of Nature");
   }
}
