package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThreadFloatingIsland extends Thread {
   private World worldObj;
   private Random random;
   private int x;
   private int y;
   private int z;
   private WorldGenFloatingIsland worldGen;

   public ThreadFloatingIsland(WorldGenFloatingIsland worldGenFloatingIsland, World world, Random rand, int pX, int pY, int pZ) {
      this.worldObj = world;
      this.random = rand;
      this.x = pX;
      this.y = pY;
      this.z = pZ;
      this.worldGen = worldGenFloatingIsland;
   }

   @Override
   public void run() {
      this.worldGen.generate(this.worldObj, this.random, new BlockPos(this.x, this.y, this.z), 3);
      this.worldGen.finished = true;
   }
}
