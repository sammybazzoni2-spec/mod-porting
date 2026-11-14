package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Candy bed. The vanilla BlockBed relies on a tile entity + builtin renderer, so this is a plain
 * two-block bed with a JSON model, reusing the vanilla PART / OCCUPIED properties.
 */
public class BlockCandyBed extends BlockHorizontal {
   public static final net.minecraft.block.properties.PropertyEnum<BlockBed.EnumPartType> PART = BlockBed.PART;
   public static final net.minecraft.block.properties.PropertyBool OCCUPIED = BlockBed.OCCUPIED;
   private static final AxisAlignedBB BED_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);

   public BlockCandyBed() {
      super(Material.CLOTH);
      this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(OCCUPIED, false).withProperty(FACING, EnumFacing.NORTH));
      this.setHardness(0.2F);
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, FACING, PART, OCCUPIED);
   }

   @Override
   public IBlockState getStateFromMeta(int meta) {
      EnumFacing facing = EnumFacing.byHorizontalIndex(meta);
      return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, BlockBed.EnumPartType.HEAD).withProperty(FACING, facing).withProperty(OCCUPIED, (meta & 4) > 0) : this.getDefaultState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(FACING, facing);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int meta = state.getValue(FACING).getHorizontalIndex();
      if (state.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         meta |= 8;
         if (state.getValue(OCCUPIED)) {
            meta |= 4;
         }
      }

      return meta;
   }

   @Override
   public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.Entity player) {
      return true;
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
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BED_AABB;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (world.isRemote) {
         return true;
      }

      if (state.getValue(PART) != BlockBed.EnumPartType.HEAD) {
         pos = pos.offset(state.getValue(FACING));
         state = world.getBlockState(pos);
         if (state.getBlock() != this) {
            return true;
         }
      }

      if (!world.provider.canRespawnHere()) {
         world.setBlockToAir(pos);
         BlockPos foot = pos.offset(state.getValue(FACING).getOpposite());
         if (world.getBlockState(foot).getBlock() == this) {
            world.setBlockToAir(foot);
         }

         world.newExplosion(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 5.0F, true, true);
         return true;
      }

      if (state.getValue(OCCUPIED)) {
         EntityPlayer occupant = this.getPlayerInBed(world, pos);
         if (occupant != null) {
            player.sendStatusMessage(new TextComponentTranslation("tile.bed.occupied"), true);
            return true;
         }

         state = state.withProperty(OCCUPIED, false);
         world.setBlockState(pos, state, 4);
      }

      EntityPlayer.SleepResult result = player.trySleep(pos);
      if (result == EntityPlayer.SleepResult.OK) {
         state = state.withProperty(OCCUPIED, true);
         world.setBlockState(pos, state, 4);
         return true;
      } else {
         if (result == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW) {
            player.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep"), true);
         } else if (result == EntityPlayer.SleepResult.NOT_SAFE) {
            player.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe"), true);
         } else if (result == EntityPlayer.SleepResult.TOO_FAR_AWAY) {
            player.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway"), true);
         }

         return true;
      }
   }


   private EntityPlayer getPlayerInBed(World world, BlockPos pos) {
      for (EntityPlayer player : world.playerEntities) {
         if (player.isPlayerSleeping() && player.bedLocation != null && player.bedLocation.equals(pos)) {
            return player;
         }
      }

      return null;
   }

   @Override
   public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
      EnumFacing facing = state.getValue(FACING);
      if (state.getValue(PART) == BlockBed.EnumPartType.FOOT) {
         if (world.getBlockState(pos.offset(facing)).getBlock() != this) {
            world.setBlockToAir(pos);
         }
      } else if (world.getBlockState(pos.offset(facing.getOpposite())).getBlock() != this) {
         world.setBlockToAir(pos);
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return state.getValue(PART) == BlockBed.EnumPartType.HEAD ? Items.AIR : CandyCraft.CottonCandyBed;
   }

   @Override
   public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
      return new ItemStack(CandyCraft.CottonCandyBed);
   }

   @Override
   public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (player.capabilities.isCreativeMode && state.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         BlockPos foot = pos.offset(state.getValue(FACING).getOpposite());
         if (world.getBlockState(foot).getBlock() == this) {
            world.setBlockToAir(foot);
         }
      }
   }
}
