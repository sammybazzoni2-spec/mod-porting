package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCandyLeave extends BlockLeaves {
   public static final String[][] field_150130_N = new String[][]{{"B6", "B26", "B49", "B79"}, {"B6_1", "B26_1", "B49_1", "B79_1"}};
   public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);

   protected BlockCandyLeave() {
      this.setTickRandomly(true);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, 0).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, VARIANT, CHECK_DECAY, DECAYABLE);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, meta & 3).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int meta = state.getValue(VARIANT);
      if (!state.getValue(DECAYABLE)) {
         meta |= 4;
      }

      if (state.getValue(CHECK_DECAY)) {
         meta |= 8;
      }

      return meta;
   }

   @Override
   public BlockPlanks.EnumType getWoodType(int meta) {
      return BlockPlanks.EnumType.OAK;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(CandyCraft.CandySapling);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return state.getValue(VARIANT);
   }

   protected Random rand(IBlockAccess world) {
      return world instanceof World ? ((World)world).rand : new Random();
   }

   @Override
   public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      Random rand = this.rand(world);
      int variant = state.getValue(VARIANT);
      int chance = 20;
      if (variant == 3) {
         chance = 40;
      }

      if (rand.nextInt(chance) == 0) {
         drops.add(new ItemStack(Item.getItemFromBlock(CandyCraft.CandySapling), 1, variant));
      }

      if (variant == 0 && rand.nextInt(60) == 0) {
         drops.add(new ItemStack(Items.DYE, 1, 3));
      }
   }

   @Override
   public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
      return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT)));
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

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         if (state.getValue(VARIANT) == 3 && world.isAirBlock(pos.down()) && rand.nextInt(100) == 0) {
            world.setBlockState(pos.down(), CandyCraft.CherryBlock.getDefaultState());
         }

         super.updateTick(world, pos, state, rand);
      }
   }
}
