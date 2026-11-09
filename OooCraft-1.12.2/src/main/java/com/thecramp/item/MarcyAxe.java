package com.thecramp.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MarcyAxe extends ItemAxe {
   public MarcyAxe(Item.ToolMaterial material) {
      // 1.7.10 axes dealt 3 + material damage; Forge's 3-arg constructor avoids vanilla's per-material arrays.
      super(material, 3.0F + material.getAttackDamage(), -3.0F);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("RIGHT-CLICK: None");
      tooltip.add("SPECIAL ATTACK: Tree Cutter");
   }
}
