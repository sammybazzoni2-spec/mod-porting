package com.thecramp.item;

import com.thecramp.Main.MainRegistry;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Pancake extends ItemFood {
   public Pancake(int hunger, float saturation, boolean isWolfFood) {
      super(hunger, saturation, isWolfFood);
      this.setCreativeTab(MainRegistry.tabFood);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("One of Jake's Favourite Foods. With Something Missing...");
      tooltip.add("'Makin' Pancakes! Makin' _____ Pancakes!'");
   }
}
