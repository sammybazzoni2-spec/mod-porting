package com.thecramp.lib;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSounds {
   public static SoundEvent HOLO_MESSAGE;
   public static SoundEvent ULTIMATE_SERUM;
   public static SoundEvent GENIUS;

   public static SoundEvent create(String name) {
      ResourceLocation loc = new ResourceLocation(RefStrings.MODID, name);
      return new SoundEvent(loc).setRegistryName(loc);
   }
}
