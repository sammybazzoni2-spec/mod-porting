package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class ItemCandyBlock extends ItemMultiTexture {
   public ItemCandyBlock(Block block) {
      super(block, block, new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"});
   }
}
