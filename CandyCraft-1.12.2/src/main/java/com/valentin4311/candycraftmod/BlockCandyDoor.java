package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCandyDoor extends BlockDoor {
   protected BlockCandyDoor(Material material) {
      super(material);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : CandyCraft.CandyDoorI;
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(CandyCraft.CandyDoorI);
   }
}
