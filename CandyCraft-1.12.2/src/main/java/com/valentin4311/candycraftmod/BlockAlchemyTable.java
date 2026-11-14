package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlchemyTable extends BlockContainer {
   protected BlockAlchemyTable(Material material) {
      super(material);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack held = player.getHeldItem(hand);
      TileEntity te = world.getTileEntity(pos);
      if (!(te instanceof TileEntityAlchemy) || held.isEmpty()) {
         return false;
      }

      TileEntityAlchemy table = (TileEntityAlchemy)te;
      if (held.getItem() == CandyCraft.GrenadineBucket) {
         if (!table.isTopFilled()) {
            table.setTopFilled(true);
            player.setHeldItem(hand, new ItemStack(Items.BUCKET));
            if (world.isRemote) {
               table.refreshPackets();
            }

            return true;
         } else if (table.getLiquid() < 7) {
            table.setLiquid(table.getLiquid() + 1);
            player.setHeldItem(hand, new ItemStack(Items.BUCKET));
            if (world.isRemote) {
               table.refreshPackets();
            }

            return true;
         } else {
            return false;
         }
      } else if (held.getItem() == Items.BUCKET) {
         if (table.isTopFilled()) {
            table.setTopFilled(false);
            held.shrink(1);
            if (!player.inventory.addItemStackToInventory(new ItemStack(CandyCraft.GrenadineBucket))) {
               player.dropItem(CandyCraft.GrenadineBucket, 1);
            }

            if (world.isRemote) {
               table.refreshPackets();
            }

            return true;
         } else if (table.getLiquid() > 0) {
            table.setLiquid(table.getLiquid() - 1);
            held.shrink(1);
            if (!player.inventory.addItemStackToInventory(new ItemStack(CandyCraft.GrenadineBucket))) {
               player.dropItem(CandyCraft.GrenadineBucket, 1);
            }

            if (world.isRemote) {
               table.refreshPackets();
            }

            return true;
         } else {
            return false;
         }
      } else if (table.isTopFilled() && table.addPotionToRecipes(held)) {
         if (held.getItem() != CandyCraft.CaramelBucket) {
            held.shrink(1);
         } else {
            player.setHeldItem(hand, new ItemStack(Items.BUCKET));
         }

         table.refreshPackets();
         return true;
      } else {
         return false;
      }
   }

   @Override
   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int meta) {
      return new TileEntityAlchemy();
   }

   @Override
   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.INVISIBLE;
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

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      TileEntity te = world.getTileEntity(pos);
      if (te instanceof TileEntityAlchemy) {
         TileEntityAlchemy table = (TileEntityAlchemy)te;
         for (int l = 0; l < table.getIngredientsCount() * 2; ++l) {
            double d0 = (double)((float)pos.getX() + rand.nextFloat() / 2.0F);
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat() / 2.0F);
            world.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + 0.25D, d1 + 0.8D, d2 + 0.25D, rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
         }
      }
   }
}
