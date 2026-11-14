package com.valentin4311.candycraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class BlockCandyGlassPane extends BlockPane {
   protected BlockCandyGlassPane(String side, String top, Material material, boolean canDrop) {
      super(material, canDrop);
   }

   public final boolean connect2(Block block) {
      return block == CandyCraft.CaramelGlass0 || block == CandyCraft.CaramelGlass1 || block == CandyCraft.CaramelGlass2;
   }
}
