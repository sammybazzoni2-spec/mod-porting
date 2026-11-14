package com.valentin4311.candycraftmod;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWiki extends Item {
   public ItemWiki() {
      this.maxStackSize = 1;
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      if (world.isRemote) {
         try {
            openWebpage((new URL("http://candycraft.wikia.com/wiki/CandyCraft_Wiki")).toURI());
         } catch (MalformedURLException e) {
            e.printStackTrace();
         } catch (URISyntaxException e) {
            e.printStackTrace();
         }
      }
      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
   }

   public static void openWebpage(URI uri) {
      Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
      if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
         try {
            desktop.browse(uri);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}
