package com.thecramp.item;

import com.thecramp.entity.EntityLemongrab;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLemon extends Item {
   @Override
   public boolean hasEffect(ItemStack stack) {
      return true;
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!world.isRemote) {
         EntityLemongrab ent = new EntityLemongrab(world);
         ent.setLocationAndAngles((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
         world.spawnEntity(ent);
         player.getHeldItem(hand).shrink(1);
      }
      return EnumActionResult.SUCCESS;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add("Place On The Floor To Spawn Lemograb!");
      tooltip.add("'Mmmm... UNACCEPTABLE!'");
   }
}
