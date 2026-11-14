package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockCandySapling extends BlockBush implements IGrowable {
   public static final String[] WOOD_TYPES = new String[]{"Guimauvier"};
   public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);
   public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
   protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

   protected BlockCandySapling() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, 0).withProperty(STAGE, 0));
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, VARIANT, STAGE);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, meta & 3).withProperty(STAGE, (meta & 8) >> 3);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(VARIANT) | (state.getValue(STAGE) << 3);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return state.getValue(VARIANT);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return SAPLING_AABB;
   }

   @Override
   protected boolean canSustainBush(IBlockState state) {
      return this.canPlaceBlockOn(state.getBlock());
   }

   protected boolean canPlaceBlockOn(Block block) {
      return block == CandyCraft.PuddingBlock || block == CandyCraft.FlourBlock || block == CandyCraft.CandySoil;
   }

   public void markOrGrowMarked(World world, BlockPos pos, Random rand) {
      IBlockState state = world.getBlockState(pos);
      if (state.getValue(STAGE) == 0) {
         world.setBlockState(pos, state.withProperty(STAGE, 1), 4);
      } else {
         this.growTree(world, pos, rand);
      }
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         super.updateTick(world, pos, state, rand);
         if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.markOrGrowMarked(world, pos, rand);
         }
      }
   }

   public void growTree(World world, BlockPos pos, Random rand) {
      if (TerrainGen.saplingGrowTree(world, rand, pos)) {
         int l = world.getBlockState(pos).getValue(VARIANT);
         WorldGenerator object = null;
         int i1 = 0;
         int j1 = 0;
         boolean flag = false;
         if (l == 0 || l == 1 || l == 2) {
            object = new WorldGenCandyTrees(true, l);
         } else {
            for (i1 = 0; i1 >= -1; --i1) {
               for (j1 = 0; j1 >= -1; --j1) {
                  if (this.isSameSapling(world, pos.add(i1, 0, j1), 3) && this.isSameSapling(world, pos.add(i1 + 1, 0, j1), 3) && this.isSameSapling(world, pos.add(i1, 0, j1 + 1), 3) && this.isSameSapling(world, pos.add(i1 + 1, 0, j1 + 1), 3)) {
                     object = new WorldGenCandyTrees(true, 10 + rand.nextInt(20), 3, l, false);
                     flag = true;
                     break;
                  }
               }

               if (object != null) {
                  break;
               }
            }

            if (object == null) {
               j1 = 0;
               i1 = 0;
               object = new WorldGenCandyTrees(true, 4 + rand.nextInt(7), 3, l, false);
            }
         }

         IBlockState self = this.getDefaultState().withProperty(VARIANT, l);
         if (flag) {
            world.setBlockToAir(pos.add(i1, 0, j1));
            world.setBlockToAir(pos.add(i1 + 1, 0, j1));
            world.setBlockToAir(pos.add(i1, 0, j1 + 1));
            world.setBlockToAir(pos.add(i1 + 1, 0, j1 + 1));
         } else {
            world.setBlockToAir(pos);
         }

         if (!object.generate(world, rand, pos.add(i1, 0, j1))) {
            if (flag) {
               world.setBlockState(pos.add(i1, 0, j1), self, 4);
               world.setBlockState(pos.add(i1 + 1, 0, j1), self, 4);
               world.setBlockState(pos.add(i1, 0, j1 + 1), self, 4);
               world.setBlockState(pos.add(i1 + 1, 0, j1 + 1), self, 4);
            } else {
               world.setBlockState(pos, self, 4);
            }
         }
      }
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 0));
         items.add(new ItemStack(this, 1, 1));
         items.add(new ItemStack(this, 1, 2));
         items.add(new ItemStack(this, 1, 3));
      }
   }

   public boolean isSameSapling(World world, BlockPos pos, int variant) {
      IBlockState state = world.getBlockState(pos);
      return state.getBlock() == this && state.getValue(VARIANT) == variant;
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
      return true;
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return (double)world.rand.nextFloat() < 0.45D;
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      this.markOrGrowMarked(world, pos, rand);
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canSustainBush(world.getBlockState(pos.down()));
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && this.canBlockStay(world, pos, this.getDefaultState());
   }
}
