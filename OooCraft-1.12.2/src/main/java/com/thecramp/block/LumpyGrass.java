package com.thecramp.block;

import com.thecramp.Main.MainRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

// Textures (bottom/top/side) are assigned in blockstates/models: assets/ooocraft/models/block/lumpy_grass.json
public class LumpyGrass extends Block {
   public LumpyGrass() {
      super(Material.GRASS);
      this.setSoundType(SoundType.PLANT);
      this.setHarvestLevel("shovel", 0);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(MainRegistry.lumpyDirt);
   }
}
