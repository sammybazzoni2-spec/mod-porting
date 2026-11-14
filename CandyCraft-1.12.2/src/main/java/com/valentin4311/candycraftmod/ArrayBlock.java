package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class ArrayBlock {
   public int x;
   public int y;
   public int z;
   public int metadata;
   public Block block;
   public IBlockState state;

   public ArrayBlock(int pX, int pY, int pZ, Block b, int m) {
      this.x = pX;
      this.y = pY;
      this.z = pZ;
      this.block = b;
      this.metadata = m;
      this.state = b.getStateFromMeta(m);
   }
}
