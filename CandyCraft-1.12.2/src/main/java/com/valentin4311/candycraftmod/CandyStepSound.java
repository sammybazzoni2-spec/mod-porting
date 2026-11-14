package com.valentin4311.candycraftmod;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Block sound type for the jelly blocks. The sound events are created here (names from sounds.json:
 * "dig.jelly", "step.jelly") and registered by {@link CandyCraft} through {@link #registerAll(IForgeRegistry)}.
 * The same cache is used for the record sounds ("records.CD-1" ...).
 * NOTE: other classes (CandySounds) must NOT create/register events with these names again.
 */
public class CandyStepSound extends SoundType {
   private static final Map<String, SoundEvent> EVENTS = new LinkedHashMap<String, SoundEvent>();
   public final String stepSoundName;

   public CandyStepSound(String name, float volume, float pitch) {
      super(volume, pitch, getOrCreate("dig." + name), getOrCreate("step." + name), getOrCreate("dig." + name), getOrCreate("step." + name), getOrCreate("step." + name));
      this.stepSoundName = name;
   }

   public static synchronized SoundEvent getOrCreate(String name) {
      SoundEvent event = EVENTS.get(name);
      if (event == null) {
         ResourceLocation rl = new ResourceLocation("candycraftmod", name);
         event = new SoundEvent(rl);
         event.setRegistryName(rl);
         EVENTS.put(name, event);
      }
      return event;
   }

   public static synchronized void registerAll(IForgeRegistry<SoundEvent> registry) {
      for (SoundEvent event : EVENTS.values()) {
         if (!registry.containsKey(event.getRegistryName())) {
            registry.register(event);
         }
      }
   }
}
