package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandyFlower extends BlockBush {
   private static final AxisAlignedBB BOX = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.9D, 0.9D);

   protected BlockCandyFlower() {
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return BOX;
   }

   @Override
   protected boolean canSustainBush(IBlockState state) {
      Block block = state.getBlock();
      return block == CandyCraft.PuddingBlock || block == CandyCraft.FlourBlock || block == CandyCraft.CandySoil;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
      if (this == CandyCraft.PoisonousFlower && rand.nextInt(4) == 2) {
         double d0 = (double)((float)pos.getX() + rand.nextFloat() / 2.0F);
         double d1 = (double)((float)pos.getY() + rand.nextFloat());
         double d2 = (double)((float)pos.getZ() + rand.nextFloat() / 2.0F);
         world.spawnParticle(EnumParticleTypes.SPELL_MOB, d0 + 0.25D, d1 + 0.5D, d2 + 0.25D, 0.1D, 0.8D, 0.1D);
      }

      if (this == CandyCraft.GoldenFlower && rand.nextInt(4) == 2) {
         double d0 = (double)((float)pos.getX() + rand.nextFloat() / 2.0F);
         double d1 = (double)((float)pos.getY() + rand.nextFloat());
         double d2 = (double)((float)pos.getZ() + rand.nextFloat() / 2.0F);
         world.spawnParticle(EnumParticleTypes.REDSTONE, d0 + 0.25D, d1 + 0.5D, d2 + 0.25D, 0.9D + (double)(rand.nextFloat() / 10.0F), 0.7D + (double)(rand.nextFloat() / 10.0F), 0.1D);
      }
   }

   @Override
   public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (this == CandyCraft.PoisonousFlower && entity instanceof EntityLivingBase) {
         ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 150, 1));
      }
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
