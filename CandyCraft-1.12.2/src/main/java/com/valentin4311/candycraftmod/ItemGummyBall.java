package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Metadata selects the model only: 0 = normal (i43), 1 = flame (i43_0), 2 = chewing gum (i43_1).
 * Renderers can use new ItemStack(CandyCraft.GummyBall, 1, variant).
 */
public class ItemGummyBall extends Item {
   public ItemGummyBall() {
      this.maxStackSize = 16;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!player.capabilities.isCreativeMode) {
         stack.shrink(1);
      }
      world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!world.isRemote) {
         world.spawnEntity(new EntityGummyBall(world, player, 0));
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
   }
}
