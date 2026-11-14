package com.valentin4311.candycraftmod;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEmblem extends Item {
   String attr = "";

   public ItemEmblem(String desc) {
      this.setMaxStackSize(1);
      this.attr = desc;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      String str = "\u00a72" + I18n.format("Desc." + this.attr);
      tooltip.add(str.replaceAll("Format error: ", ""));
      tooltip.add("\u00a7a" + I18n.format("Desc.Emblem"));
   }
}
