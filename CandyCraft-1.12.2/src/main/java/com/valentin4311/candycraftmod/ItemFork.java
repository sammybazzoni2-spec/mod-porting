package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFork extends Item {
   public ItemFork() {
      this.maxStackSize = 1;
      this.setMaxDamage(100);
   }

   @Override
   public int getItemEnchantability() {
      return 1;
   }

   @Override
   public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
      World world = player.world;
      IBlockState state = world.getBlockState(pos);
      Block i = state.getBlock();
      if (i != null && CandyCraft.eat != null) {
         ItemStack block = new ItemStack(i, 1, i.damageDropped(state));
         if (EnchantmentHelper.getEnchantmentLevel(CandyCraft.eat, itemstack) > 0 && TileEntitySugarFactory.isItemValid(block)) {
            world.playSound((EntityPlayer)null, pos, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            world.setBlockToAir(pos);
            itemstack.setItemDamage(Math.max(0, itemstack.getItemDamage() - 1));
            player.getFoodStats().addStats(1, 0.0F);
         }
      }
      return false;
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack stack = player.getHeldItem(hand);
      if (!player.canPlayerEdit(pos, facing, stack)) {
         return EnumActionResult.FAIL;
      }
      Block i1 = world.getBlockState(pos).getBlock();
      if (i1 != CandyCraft.PuddingBlock && i1 != CandyCraft.FlourBlock) {
         return EnumActionResult.FAIL;
      }
      Block block = CandySoilHolder.soil();
      SoundType st = block.getSoundType();
      world.playSound(player, pos, st.getStepSound(), SoundCategory.BLOCKS, (st.getVolume() + 1.0F) / 2.0F, st.getPitch() * 0.8F);
      if (world.isRemote) {
         return EnumActionResult.SUCCESS;
      }
      world.setBlockState(pos, block.getDefaultState(), 3);
      stack.damageItem(1, player);
      return EnumActionResult.SUCCESS;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isFull3D() {
      return true;
   }

   private static class CandySoilHolder {
      static Block soil() {
         return CandyCraft.CandySoil;
      }
   }
}
