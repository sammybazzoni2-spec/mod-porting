package com.thecramp.Main;

import com.thecramp.lib.RefStrings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = RefStrings.MODID, value = Side.CLIENT)
public class ModelHandler {
   @SubscribeEvent
   public static void registerModels(ModelRegistryEvent event) {
      for (Item item : MainRegistry.ALL_ITEMS) {
         ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
      }
   }
}
