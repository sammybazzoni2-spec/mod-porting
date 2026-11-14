package com.valentin4311.candycraftmod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class WikiCommand extends CommandBase {
   private List<String> aliases = new ArrayList<String>();

   public WikiCommand() {
      this.aliases.add("candywiki");
   }

   @Override
   public String getName() {
      return "candywiki";
   }

   @Override
   public String getUsage(ICommandSender sender) {
      return "/candywiki";
   }

   @Override
   public List<String> getAliases() {
      return this.aliases;
   }

   @Override
   public int getRequiredPermissionLevel() {
      return 0;
   }

   @Override
   public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
      if (sender instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)sender;
         boolean has = false;
         for (ItemStack s : player.inventory.mainInventory) {
            if (!s.isEmpty() && s.getItem() == CandyCraft.Wiki) {
               has = true;
            }
         }
         if (!has) {
            if (player.inventory.addItemStackToInventory(new ItemStack(CandyCraft.Wiki))) {
               this.msg(player, "chat.wikiOk", TextFormatting.GREEN);
            } else {
               this.msg(player, "chat.wikiRoom", TextFormatting.RED);
            }
         } else {
            this.msg(player, "chat.wikiFail", TextFormatting.RED);
         }
      }
   }

   private void msg(EntityPlayer player, String key, TextFormatting color) {
      TextComponentTranslation t = new TextComponentTranslation(key);
      t.getStyle().setColor(color);
      player.sendMessage(t);
   }

   @Override
   public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
      return sender instanceof EntityPlayer;
   }
}
