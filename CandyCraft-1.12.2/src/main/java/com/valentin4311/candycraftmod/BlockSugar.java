package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockSugar extends Block {
   public BlockSugar(Material material) {
      super(material);
   }

   @Override
   public int quantityDroppedWithBonus(int fortune, Random random) {
      return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
   }

   @Override
   public int quantityDropped(Random random) {
      return 2 + random.nextInt(3);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Items.SUGAR;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack held = player.getHeldItem(hand);
      if (held.isEmpty()) {
         return false;
      }

      boolean lava = held.getItem() == Items.LAVA_BUCKET && CandyCraftConfig.canOpenPortalWithLava;
      boolean caramel = held.getItem() == CandyCraft.CaramelBucket && CandyCraftConfig.canOpenPortalWithCaramel;
      int dim = world.provider.getDimension();
      if ((lava || caramel) && (dim == 0 || dim == CandyCraft.idDimension)) {
         boolean i = CandyCraft.CandyPortal.tryToCreatePortal(world, pos.up());
         if (i) {
            player.setHeldItem(hand, new ItemStack(Items.BUCKET));
         }

         return i;
      } else {
         return false;
      }
   }
}
