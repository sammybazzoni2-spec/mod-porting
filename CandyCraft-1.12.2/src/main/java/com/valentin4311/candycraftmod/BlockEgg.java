package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEgg extends BlockDragonEgg implements ITileEntityProvider {
   private final boolean beetle;

   /** The drop item is resolved lazily because the items are created after the blocks. */
   protected BlockEgg(boolean beetle) {
      this.beetle = beetle;
      this.setTickRandomly(true);
   }

   private Item dropItem() {
      return this.beetle ? CandyCraft.BeetleEgg : CandyCraft.MysteriousEgg;
   }

   @Override
   public int tickRate(World world) {
      return 10;
   }

   @Override
   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      return false;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      boolean readyToHatch = true;
      for (int x = 0; x < 5; ++x) {
         for (int z = 0; z < 5; ++z) {
            if ((x == 0 || x == 4 || z == 0 || z == 4) && world.getBlockState(pos.add(-2 + x, 0, -2 + z)).getBlock() != CandyCraft.GoldenFlower) {
               readyToHatch = false;
            }
         }
      }

      if (readyToHatch) {
         for (int i = 0; i < 7; ++i) {
            double d0 = (double)((float)pos.getX() + rand.nextFloat() / 2.0F);
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat() / 2.0F);
            world.spawnParticle(EnumParticleTypes.REDSTONE, d0 + 0.25D, d1 + 0.6D, d2 + 0.25D, 0.9D + (double)(rand.nextFloat() / 10.0F), 0.7D + (double)(rand.nextFloat() / 10.0F), 0.35D + (double)(rand.nextFloat() / 10.0F));
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return this.dropItem();
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(this.dropItem());
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      this.checkAndDropBlock(world, pos, state);
   }

   protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
      if (!this.canBlockStay(world, pos)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      world.removeTileEntity(pos);
   }

   @Override
   public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
      super.eventReceived(state, world, pos, id, param);
      TileEntity tileentity = world.getTileEntity(pos);
      return tileentity != null ? tileentity.receiveClientEvent(id, param) : false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).isOpaqueCube();
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityEgg();
   }
}
