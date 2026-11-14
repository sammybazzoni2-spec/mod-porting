package com.valentin4311.candycraftmod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemKey extends Item {
   String texture;

   public ItemKey(String tex) {
      this.texture = tex;
      this.setMaxStackSize(1);
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack stack = player.getHeldItem(hand);
      if (this.texture.toLowerCase().contains("i20")) {
         if (player.canPlayerEdit(pos, facing, stack) && CandyCraftConfig.canGenerateDungeon) {
            IBlockState state = world.getBlockState(pos);
            if (state.isFullCube() && !world.isRemote) {
               BlockPos up = pos.up();
               world.setBlockState(up, CandyCraft.BlockTeleporter.getDefaultState(), 2);
               player.setHeldItem(hand, ItemStack.EMPTY);
               TextComponentTranslation msg = new TextComponentTranslation("chat.generating");
               msg.getStyle().setColor(net.minecraft.util.text.TextFormatting.YELLOW);
               player.sendMessage(msg);
               ThreadCheckDungeon d = new ThreadCheckDungeon();
               d.teleport = (TileEntityTeleporter)world.getTileEntity(up);
               d.player = player;
               d.px = up.getX();
               d.py = up.getY();
               d.pz = up.getZ();
               d.dim = world.provider.getDimension();
               d.start();
               return EnumActionResult.SUCCESS;
            }
         }
      }
      return EnumActionResult.FAIL;
   }
}
