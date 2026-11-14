package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockHoneyOre extends Block {
   public BlockHoneyOre(Material material) {
      super(material);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return CandyCraft.HoneyShard;
   }

   @Override
   public int quantityDropped(Random random) {
      return 2 + random.nextInt(2);
   }
}
