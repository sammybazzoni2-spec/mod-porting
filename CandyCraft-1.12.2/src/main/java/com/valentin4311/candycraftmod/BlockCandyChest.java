package com.valentin4311.candycraftmod;

import net.minecraft.block.BlockChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCandyChest extends BlockChest {
   protected BlockCandyChest(int type) {
      super(BlockChest.Type.BASIC);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityCandyChest();
   }
}
