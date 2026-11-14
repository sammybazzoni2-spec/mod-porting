package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPudding extends Block {
   protected BlockPudding() {
      super(Material.GROUND);
      this.setTickRandomly(true);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         BlockPos up = pos.up();
         if (world.getLightFromNeighbors(up) < 4 && world.getBlockState(up).getLightOpacity(world, up) > 2) {
            world.setBlockState(pos, CandyCraft.FlourBlock.getDefaultState());
         } else if (world.getLightFromNeighbors(up) >= 9) {
            for (int l = 0; l < 4; ++l) {
               BlockPos p = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
               BlockPos pUp = p.up();
               if (world.getBlockState(p).getBlock() == CandyCraft.FlourBlock && world.getLightFromNeighbors(pUp) >= 4 && world.getBlockState(pUp).getLightOpacity(world, pUp) <= 2) {
                  world.setBlockState(p, CandyCraft.PuddingBlock.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT_MIPPED;
   }

   @Override
   public int quantityDropped(Random random) {
      return 1;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(CandyCraft.FlourBlock);
   }
}
