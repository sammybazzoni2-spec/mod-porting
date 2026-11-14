package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockNougat extends Block {
   protected BlockNougat() {
      super(Material.ROCK);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return CandyCraft.NougatPowder;
   }

   @Override
   public int quantityDropped(Random random) {
      return random.nextInt(4) + 3;
   }
}
