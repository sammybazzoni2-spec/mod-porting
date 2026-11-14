package com.valentin4311.candycraftmod;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ThreadCheckDungeon extends Thread {
   EntityPlayer player = null;
   TileEntityTeleporter teleport = null;
   int px;
   int py;
   int pz;
   int dim = 0;

   @Override
   public void run() {
      World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(CandyCraft.idDimension2);
      int x = 0;
      int id = 0;
      while (world.getBlockState(new BlockPos(x, 64, 0)).getMaterial() != Material.AIR) {
         x += 1000;
         ++id;
      }
      this.teleport.x = x;
      this.teleport.y = 65;
      this.teleport.z = 0;
      this.teleport.oX = this.px;
      this.teleport.oY = this.py;
      this.teleport.oZ = this.pz;
      this.teleport.dim = this.dim;
      this.teleport.player = this.player;
      this.teleport.dungeonID = id;
   }
}
