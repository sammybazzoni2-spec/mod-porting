package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCandyChest extends TileEntityChest {
   @SideOnly(Side.CLIENT)
   @Override
   public AxisAlignedBB getRenderBoundingBox() {
      AxisAlignedBB bb = INFINITE_EXTENT_AABB;
      Block type = this.getBlockType();
      if (type == CandyCraft.CandyChest) {
         bb = new AxisAlignedBB((double)(this.pos.getX() - 1), (double)this.pos.getY(), (double)(this.pos.getZ() - 1), (double)(this.pos.getX() + 2), (double)(this.pos.getY() + 2), (double)(this.pos.getZ() + 2));
      }

      return bb;
   }
}
