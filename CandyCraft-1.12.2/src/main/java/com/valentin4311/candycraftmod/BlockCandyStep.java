package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCandyStep extends BlockSlab {
   public enum Variant implements IStringSerializable {
      DEFAULT;

      @Override
      public String getName() {
         return "default";
      }
   }

   public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);
   private static boolean pendingDouble;
   private boolean built;
   String texture;
   String texture2;
   int dropped = 0;
   boolean full = false;

   private static Material prepare(boolean isDouble, Material material) {
      pendingDouble = isDouble;
      return material;
   }

   public BlockCandyStep(boolean isDouble, String tex, String tex2, Material material, int dropped) {
      super(prepare(isDouble, material));
      this.texture = tex;
      this.texture2 = tex2;
      this.full = isDouble;
      this.dropped = dropped;
      IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
      if (!isDouble) {
         state = state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
      }

      this.setDefaultState(state);
      this.useNeighborBrightness = !isDouble;
      this.built = true;
   }

   @Override
   public boolean isDouble() {
      return this.built ? this.full : pendingDouble;
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return pendingDouble ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      IBlockState state = this.getDefaultState().withProperty(VARIANT, Variant.DEFAULT);
      if (!this.isDouble()) {
         state = state.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
      }

      return state;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return !this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP ? 8 : 0;
   }

   @Override
   public String getTranslationKey(int meta) {
      return this.getTranslationKey();
   }

   @Override
   public IProperty<?> getVariantProperty() {
      return VARIANT;
   }

   @Override
   public Comparable<?> getTypeForItem(ItemStack stack) {
      return Variant.DEFAULT;
   }

   private BlockSlab halfStep() {
      switch (this.dropped) {
         case 0:
            return CandyCraft.CandyHalfStep;
         case 1:
            return CandyCraft.LicoriceHalfStep;
         case 2:
            return CandyCraft.BarleyHalfStep;
         case 3:
            return CandyCraft.CottonCandyHalfStep;
         case 4:
            return CandyCraft.CandyHalfStep2;
         case 5:
            return CandyCraft.CandyHalfStep3;
         case 6:
            return CandyCraft.IceCreamHalfStep0;
         case 7:
            return CandyCraft.IceCreamHalfStep1;
         case 8:
            return CandyCraft.IceCreamHalfStep2;
         case 9:
            return CandyCraft.IceCreamHalfStep3;
         default:
            return null;
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      BlockSlab half = this.halfStep();
      return half == null ? Items_AIR() : Item.getItemFromBlock(half);
   }

   private static Item Items_AIR() {
      return net.minecraft.init.Items.AIR;
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      BlockSlab half = this.halfStep();
      return half == null ? ItemStack.EMPTY : new ItemStack(half);
   }

   @Override
   protected ItemStack getSilkTouchDrop(IBlockState state) {
      BlockSlab half = this.halfStep();
      return half == null ? ItemStack.EMPTY : new ItemStack(half, this.isDouble() ? 2 : 1, 0);
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (!this.full && (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH)) {
         items.add(new ItemStack(this, 1, 0));
      }
   }
}
