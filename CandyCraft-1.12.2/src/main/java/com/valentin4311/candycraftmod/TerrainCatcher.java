package com.valentin4311.candycraftmod;

import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainCatcher {
   @SubscribeEvent
   public void setBiomes(WorldTypeEvent.InitBiomeGens event) {
      if (event.getWorldType() instanceof WorldTypeCandy) {
         event.setNewBiomeGens(GenLayerCandyBiomes.initBiomes(event.getSeed(), event.getWorldType()));
      }
   }
}
