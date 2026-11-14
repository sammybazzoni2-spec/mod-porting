package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTeleporter extends BlockContainer {
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.06D, 0.8D);

   public BlockTeleporter(Material material) {
      super(material);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BOX;
   }

   @Override
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }

   @Override
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   @Override
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   @Override
   public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }

   @Override
   public int quantityDropped(Random random) {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      for (int i = 0; i < 10; ++i) {
         this.sparkle(world, pos.getX(), (double)pos.getY() + (double)i / 5.0D, pos.getZ(), rand);
      }
   }

   private void sparkle(World world, int x, double y, int z, Random rand) {
      double d0 = (double)x + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
      double d1 = (double)((float)y + 0.125F);
      double d2 = (double)z + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
      world.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 1.0D, 0.5D, 0.5D);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).isOpaqueCube();
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return ItemStack.EMPTY;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      TileEntity te = world.getTileEntity(pos);
      if (!(te instanceof TileEntityTeleporter)) {
         return true;
      }

      TileEntityTeleporter tile = (TileEntityTeleporter)te;
      if (!world.isRemote && !player.isRiding() && !player.isBeingRidden() && player instanceof EntityPlayerMP && tile.generated) {
         EntityPlayerMP mp = (EntityPlayerMP)player;
         mp.setPositionAndUpdate((double)tile.x, (double)tile.y, (double)tile.z);
         if (world.provider.getDimension() != CandyCraft.idDimension2) {
            mp.server.getPlayerList().transferPlayerToDimension(mp, CandyCraft.idDimension2, new TeleporterDungeon(mp.server.getWorld(CandyCraft.idDimension2), tile));
         } else {
            mp.server.getPlayerList().transferPlayerToDimension(mp, tile.dim, new TeleporterDungeon(mp.server.getWorld(tile.dim), tile));
         }
      }

      return true;
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).isOpaqueCube();
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      if (!this.canBlockStay(world, pos)) {
         world.setBlockToAir(pos);
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityTeleporter();
   }
}
