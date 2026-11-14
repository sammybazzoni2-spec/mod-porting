package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class ClientTick {
   @net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
   public Object bossHealth;
   boolean isDeclared = false;
   public static boolean threadFinished = false;
   public static String version = "";
   public static String mcVersion = "";
   public static String words = "";
   public static WorldWeather weatherController;

   /** Lazily creates the boss bar GUI (needs a running Minecraft instance). */
   public GuiBoss getBossGui() {
      if (this.bossHealth == null) {
         this.bossHealth = new GuiBoss();
         this.isDeclared = true;
      }
      return (GuiBoss)this.bossHealth;
   }

   public void onRenderTick(float tick) {
      Minecraft mc = Minecraft.getMinecraft();
      if (mc.player != null && mc.currentScreen == null) {
         this.getBossGui().drawScreen(tick);
      }
      if (mc.player != null && mc.player.world != null) {
         World world = mc.player.world;
         if (weatherController != null && weatherController.world == world) {
            if (weatherController.timeLeft > 0.0D) {
               weatherController.timeLeft -= (double)(1.0F * tick);
            } else {
               int day = (int)(world.getTotalWorldTime() / 24000L);
               if (day == 0) {
                  if (world.getTotalWorldTime() == 6000L) {
                     weatherController.timeLeft = 4800.0D;
                  } else if (world.getTotalWorldTime() > 6000L && world.getTotalWorldTime() < 7200L) {
                     weatherController.timeLeft = (double)(10800L - world.getTotalWorldTime());
                  } else {
                     weatherController.timeLeft = 0.0D;
                  }
               } else {
                  long min = world.getTotalWorldTime() + (long)(day * 6530) & 24000L;
                  long max = min + 4800L;
                  if (world.getTotalWorldTime() == min) {
                     weatherController.timeLeft = 4800.0D;
                  } else if (world.getTotalWorldTime() > min && world.getTotalWorldTime() < max) {
                     weatherController.timeLeft = (double)(max - world.getTotalWorldTime());
                  } else {
                     weatherController.timeLeft = 0.0D;
                  }
               }
            }
         } else {
            weatherController = new WorldWeather(world);
         }
      }
   }

   public static String getKeyDisplayString(int par0) {
      return par0 < 0 ? I18n.format("key.mouseButton", par0 + 101) : Keyboard.getKeyName(par0);
   }
}
