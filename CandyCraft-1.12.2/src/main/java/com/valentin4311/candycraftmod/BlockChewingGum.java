package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChewingGum extends Block {
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D - 0.125D, 1.0D);

   protected BlockChewingGum(Material material) {
      super(material);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
      return BOX;
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!(entity instanceof EntityBeetle) && !(entity instanceof EntityBossBeetle) && !(entity instanceof EntityKingBeetle)) {
         if (entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(CandyCraft.ChewingGumEmblem))) {
            return;
         }

         entity.motionX *= 0.2D;
         entity.motionZ *= 0.2D;
         entity.motionY = 0.0D;
      }
   }
}
