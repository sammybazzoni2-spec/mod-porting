package com.valentin4311.candycraftmod;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ServerTick {
   public static ArrayList<DynamiteCallBack> dynamiteCallBack = new ArrayList<DynamiteCallBack>();
   public static ArrayList<WorldGenFloatingIsland> floatingIsland = new ArrayList<WorldGenFloatingIsland>();

   public void onWorldTick(World world) {
      if (world instanceof WorldServer) {
         WorldServer worldS = (WorldServer)world;
         if (worldS.areAllPlayersAsleep()) {
            if (worldS.getGameRules().getBoolean("doDaylightCycle")) {
               long i = world.getWorldInfo().getWorldTime() + 24000L;
               this.setTime(i - i % 24000L);
            }
            this.wakeAllPlayers(worldS);
         }
         if (world.provider.getDimension() == CandyCraft.idDimension) {
            if (WorldProviderCandy.canGenVillage > 0) {
               --WorldProviderCandy.canGenVillage;
            }
            if (WorldProviderCandy.canGenTemple > 0) {
               --WorldProviderCandy.canGenTemple;
            }
            if (WorldProviderCandy.canGenIsland > 0) {
               --WorldProviderCandy.canGenIsland;
            }
            if (WorldProviderCandy.canGenHouses > 0) {
               --WorldProviderCandy.canGenHouses;
            }
            if (WorldProviderCandy.canGenGeyser > 0) {
               --WorldProviderCandy.canGenGeyser;
            }
            if (WorldProviderCandy.canGenChewingGum > 0) {
               --WorldProviderCandy.canGenChewingGum;
            }
            if (WorldProviderCandy.canGenIceTower > 0) {
               --WorldProviderCandy.canGenIceTower;
            }
            for (int i = 0; i < floatingIsland.size(); ++i) {
               WorldGenFloatingIsland is = floatingIsland.get(i);
               if (is.finished && world == is.world) {
                  floatingIsland.remove(i);
                  is.finishGeneration(is.world, new net.minecraft.util.math.BlockPos(is.x, is.y, is.z));
                  --i;
               }
            }
         }
         for (int i = 0; i < dynamiteCallBack.size(); ++i) {
            DynamiteCallBack cb = dynamiteCallBack.get(i);
            if (cb.entity != null && cb.entity.world.provider.getDimension() == world.provider.getDimension()) {
               int before = dynamiteCallBack.size();
               cb.reduce(i);
               if (dynamiteCallBack.size() < before) {
                  --i;
               }
            }
         }
      }
   }

   public void setTime(long time) {
      for (WorldServer w : FMLCommonHandler.instance().getMinecraftServerInstance().worlds) {
         w.setWorldTime(time);
      }
   }

   protected void wakeAllPlayers(WorldServer world) {
      for (EntityPlayer entityplayer : new ArrayList<EntityPlayer>(world.playerEntities)) {
         if (entityplayer.isPlayerSleeping()) {
            entityplayer.wakeUpPlayer(false, false, true);
         }
      }
      WorldInfo info = world.getWorldInfo();
      info.setRainTime(0);
      info.setRaining(false);
      info.setThunderTime(0);
      info.setThundering(false);
   }

   private static boolean hasItem(EntityPlayer player, Item item) {
      for (ItemStack s : player.inventory.mainInventory) {
         if (!s.isEmpty() && s.getItem() == item) {
            return true;
         }
      }
      for (ItemStack s : player.inventory.offHandInventory) {
         if (!s.isEmpty() && s.getItem() == item) {
            return true;
         }
      }
      return false;
   }

   public void onPlayerTick(EntityPlayer player) {
      ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
      if (!head.isEmpty() && head.getItem() == CandyCraft.WaterMask) {
         player.setAir(300);
      }
      if (hasItem(player, CandyCraft.WaterEmblem) && player.isInWater() && player.ticksExisted % 600 == 0) {
         player.heal(1.0F);
      }
      if (hasItem(player, CandyCraft.CranberryEmblem)) {
         long i = player.world.getWorldInfo().getWorldTime() + 24000L;
         if (player.world.getWorldTime() + 1L == i - i % 24000L) {
            player.heal(200.0F);
            TextComponentTranslation msg = new TextComponentTranslation("Msg.CranberryEmblem");
            msg.getStyle().setColor(TextFormatting.DARK_GREEN);
            player.sendMessage(msg);
         }
      }
   }
}
