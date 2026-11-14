package com.valentin4311.candycraftmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

/**
 * Entity sounds of the mod. The jelly block sounds and the record sounds are created and registered by
 * {@link CandyStepSound}; do not register them here.
 */
public class CandySounds {
   public static final SoundEvent NESSIE = create("mob.nessie");
   public static final SoundEvent NESSIE_HURT = create("mob.nessiehurt");

   private static SoundEvent create(String name) {
      ResourceLocation rl = new ResourceLocation("candycraftmod", name);
      SoundEvent event = new SoundEvent(rl);
      event.setRegistryName(rl);
      return event;
   }

   public static void register(RegistryEvent.Register<SoundEvent> event) {
      event.getRegistry().register(NESSIE);
      event.getRegistry().register(NESSIE_HURT);
   }
}
