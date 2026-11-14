package com.valentin4311.candycraftmod;

import net.minecraft.world.World;

public class WorldWeather {
   public World world;
   public double timeLeft = (double)0.0F;

   public WorldWeather(World w) {
      this.world = w;
   }
}
