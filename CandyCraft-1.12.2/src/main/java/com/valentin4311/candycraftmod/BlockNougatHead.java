package com.valentin4311.candycraftmod;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNougatHead extends BlockHorizontal {
   protected BlockNougatHead() {
      super(Material.IRON);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(FACING).getHorizontalIndex();
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 1));
      }
   }

   @Override
   public int damageDropped(IBlockState state) {
      return 1;
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(this, 1, 1);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      if (world.getBlockState(pos.down()).getBlock() == CandyCraft.NougatBlock && world.getBlockState(pos.down(2)).getBlock() == CandyCraft.NougatBlock) {
         int lenght = 0;
         for (int i = 0; i < 256 && world.getBlockState(pos.down(i + 1)).getBlock() == CandyCraft.NougatBlock; ++i) {
            world.setBlockToAir(pos.down(i + 1));
            ++lenght;
         }

         world.setBlockToAir(pos);
         EntityNougatGolem[] golemList = new EntityNougatGolem[lenght + 1];
         for (int j = 0; j < lenght + 1; ++j) {
            EntityNougatGolem golem = new EntityNougatGolem(world);
            golem.setPosition((double)pos.getX() + 0.5D, (double)(pos.getY() - lenght), (double)pos.getZ() + 0.5D);
            if (j == 0) {
               golem.setLenght(0.8F);
            }

            golemList[j] = golem;
            if (j != 0) {
               golem.startRiding(golemList[j - 1]);
            }

            world.spawnEntity(golem);
         }
      }
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
   }
}
