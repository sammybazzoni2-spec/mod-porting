package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyFurnace extends BlockContainer {
   public static final net.minecraft.block.properties.PropertyDirection FACING = BlockHorizontal.FACING;
   private final boolean isBurning;
   private static boolean keepInventory;

   protected BlockCandyFurnace(boolean burning) {
      super(Material.ROCK);
      this.isBurning = burning;
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH));
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      EnumFacing facing = EnumFacing.byIndex(meta & 7);
      if (facing.getAxis() == EnumFacing.Axis.Y) {
         facing = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, facing);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return state.getValue(FACING).getIndex();
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Item.getItemFromBlock(CandyCraft.SugarFurnace);
   }

   @Override
   public int damageDropped(IBlockState state) {
      return 3;
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(CandyCraft.SugarFurnace);
   }

   @Override
   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.getCreativeTab() == tab || tab == CreativeTabs.SEARCH) {
         items.add(new ItemStack(this, 1, 3));
      }
   }

   @Override
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (world.isRemote) {
         return true;
      } else {
         TileEntity te = world.getTileEntity(pos);
         if (te instanceof TileEntitySugarFurnace) {
            player.openGui(CandyCraft.modInstance, 1, world, pos.getX(), pos.getY(), pos.getZ());
         }

         return true;
      }
   }

   public static void updateFurnaceBlockState(boolean burning, World world, BlockPos pos) {
      IBlockState old = world.getBlockState(pos);
      TileEntity tileentity = world.getTileEntity(pos);
      keepInventory = true;
      if (burning) {
         world.setBlockState(pos, CandyCraft.SugarFurnaceOn.getDefaultState().withProperty(FACING, old.getValue(FACING)), 3);
      } else {
         world.setBlockState(pos, CandyCraft.SugarFurnace.getDefaultState().withProperty(FACING, old.getValue(FACING)), 3);
      }

      keepInventory = false;
      if (tileentity != null) {
         tileentity.validate();
         world.setTileEntity(pos, tileentity);
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntitySugarFurnace();
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
      world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
      if (stack.hasDisplayName()) {
         TileEntity te = world.getTileEntity(pos);
         if (te instanceof TileEntitySugarFurnace) {
            ((TileEntitySugarFurnace)te).setCustomName(stack.getDisplayName());
         }
      }
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (!keepInventory) {
         TileEntity te = world.getTileEntity(pos);
         if (te instanceof TileEntitySugarFurnace) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory)te);
            world.updateComparatorOutputLevel(pos, this);
         }
      }

      super.breakBlock(world, pos, state);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      if (this.isBurning) {
         EnumFacing facing = state.getValue(FACING);
         double f = (double)pos.getX() + 0.5D;
         double f1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
         double f2 = (double)pos.getZ() + 0.5D;
         double f3 = 0.52D;
         double f4 = rand.nextDouble() * 0.6D - 0.3D;
         double x = f;
         double z = f2;
         switch (facing) {
            case WEST:
               x = f - f3;
               z = f2 + f4;
               break;
            case EAST:
               x = f + f3;
               z = f2 + f4;
               break;
            case NORTH:
               x = f + f4;
               z = f2 - f3;
               break;
            case SOUTH:
               x = f + f4;
               z = f2 + f3;
               break;
            default:
               return;
         }

         world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, f1, z, 0.0D, 0.0D, 0.0D);
         world.spawnParticle(EnumParticleTypes.FLAME, x, f1, z, 0.0D, 0.0D, 0.0D);
      }
   }

   @Override
   public boolean hasComparatorInputOverride(IBlockState state) {
      return true;
   }

   @Override
   public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
      return Container.calcRedstone(world.getTileEntity(pos));
   }
}
