package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {
   public static GuiEmpty emptyGui;
   public static int chestId;
   public static int puddingId;
   public static int alchemyId;
   public static ModelBiped crown;
   public static KeyBinding unleashMountPower = new KeyBinding("key.mountPower", Keyboard.KEY_P, "CandyCraft");

   @Override
   public void preInit() {
      emptyGui = new GuiEmpty();
      MinecraftForge.EVENT_BUS.register(this);
      MinecraftForge.EVENT_BUS.register(new ClientEventCatcher());
      ClientRegistry.registerKeyBinding(unleashMountPower);
      CandyRenderRegistry.registerAll();
   }

   @Override
   public void init() {
      Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> ItemGrenadineCandy.getColor(stack, tintIndex), CandyCraft.GrenadineCandy);
      crown = new ModelBiped(0.1F);
      crown.bipedHead = new ModelRenderer(crown, 0, 0);
      crown.bipedHead.addBox(-4.0F, -9.5F, -4.0F, 8, 8, 8, 0.1F);
      crown.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
   }

   @SubscribeEvent
   public void onModelRegistry(ModelRegistryEvent event) {
      for (Item item : CandyCraft.getOwnItems()) {
         if (item instanceof ItemBlock || item.getRegistryName() == null) {
            continue;
         }
         if (item == CandyCraft.GrenadineCandy) {
            final ModelResourceLocation loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelBakery.registerItemVariants(item, loc);
            ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
               public ModelResourceLocation getModelLocation(ItemStack stack) {
                  return loc;
               }
            });
         } else if (item == CandyCraft.GummyBall) {
            variants(item, "", "_0", "_1");
         } else if (item == CandyCraft.Dynamite || item == CandyCraft.GlueDynamite) {
            variants(item, "", "_on", "_warn");
         } else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
         }
      }
      // Bow / crossbow pulling models are referenced from the item json overrides.
      ModelBakery.registerItemVariants(CandyCraft.CaramelBow, new ModelResourceLocation("candycraftmod:i38", "inventory"), new ModelResourceLocation("candycraftmod:i41", "inventory"), new ModelResourceLocation("candycraftmod:i40", "inventory"), new ModelResourceLocation("candycraftmod:i39", "inventory"));
   }

   private static void variants(Item item, String... suffixes) {
      String base = item.getRegistryName().toString();
      if (item == CandyCraft.GummyBall) {
         base = "candycraftmod:i43";
      }
      for (int i = 0; i < suffixes.length; ++i) {
         String name = item == CandyCraft.GummyBall ? base + suffixes[i] : base + suffixes[i];
         ModelBakery.registerItemVariants(item, new ModelResourceLocation(name, "inventory"));
         ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(name, "inventory"));
      }
   }

   @Override
   public void registerRenderThings() {
   }

   @Override
   public void addLanguage() {
   }
}
