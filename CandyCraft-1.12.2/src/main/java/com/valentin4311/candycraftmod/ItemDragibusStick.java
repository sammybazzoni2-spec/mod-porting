package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDragibusStick extends Item {
   public ItemDragibusStick() {
      this.setMaxStackSize(1);
      this.setMaxDamage(25);
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isFull3D() {
      return true;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (player.isRiding() && player.getRidingEntity() instanceof EntityCandyPig) {
         EntityCandyPig pig = (EntityCandyPig)player.getRidingEntity();
         if (stack.getMaxDamage() - stack.getItemDamage() >= 7 && pig.boost()) {
            stack.damageItem(7, player);
            if (stack.isEmpty()) {
               ItemStack rod = new ItemStack(Items.FISHING_ROD);
               rod.setTagCompound(stack.getTagCompound());
               return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, rod);
            }
         }
      }
      return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
   }
}
