package com.valentin4311.candycraftmod;

import net.minecraft.item.Item;

/**
 * Legacy spawn egg item. In 1.12 the eggs are created by CandyEntityRegistry (EntityEntryBuilder.egg), so this
 * item is NOT registered anymore and only kept so that the class exists.
 * TODO(port): remove if nothing references it.
 */
public class ItemCandyMonsterPlacer extends Item {
   public ItemCandyMonsterPlacer() {
      this.setHasSubtypes(true);
   }
}
