package com.thecramp.dimension;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {
   public static final int dimensionId = 8;
   public static DimensionType dimensionType;

   public static void mainRegistry() {
      registerDimension();
   }

   public static void registerDimension() {
      dimensionType = DimensionType.register("Dimension1", "_dimension1", dimensionId, WorldProviderDimension1.class, false);
      DimensionManager.registerDimension(dimensionId, dimensionType);
   }
}
