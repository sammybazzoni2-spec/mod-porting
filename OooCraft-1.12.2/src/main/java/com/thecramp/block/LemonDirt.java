package com.thecramp.block;

import com.thecramp.Main.MainRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class LemonDirt extends Block {
   public LemonDirt() {
      super(Material.GRASS);
      this.setSoundType(SoundType.PLANT);
      this.setHarvestLevel("shovel", 0);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return MainRegistry.lemonShard;
   }
}
