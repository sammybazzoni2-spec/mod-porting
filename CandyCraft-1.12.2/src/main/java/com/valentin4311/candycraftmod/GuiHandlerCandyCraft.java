package com.valentin4311.candycraftmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerCandyCraft implements IGuiHandler {
   @Override
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
      if (tileEntity instanceof TileEntitySugarFactory) {
         return new ContainerSugarFactory(player.inventory, (TileEntitySugarFactory)tileEntity);
      } else if (tileEntity instanceof TileEntitySugarFurnace) {
         return new ContainerSugarFurnace(player.inventory, (TileEntitySugarFurnace)tileEntity);
      } else {
         return ID == 4301 ? new ContainerCandyWorkBench(player.inventory, world, new BlockPos(x, y, z)) : null;
      }
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
      if (tileEntity instanceof TileEntitySugarFactory) {
         return new GuiSugarFactory(player.inventory, (TileEntitySugarFactory)tileEntity);
      } else if (tileEntity instanceof TileEntitySugarFurnace) {
         return new GuiSugarFurnace(player.inventory, (TileEntitySugarFurnace)tileEntity);
      } else {
         return ID == 4301 ? new GuiCandyWorkbench(player.inventory, world, new BlockPos(x, y, z)) : null;
      }
   }
}
