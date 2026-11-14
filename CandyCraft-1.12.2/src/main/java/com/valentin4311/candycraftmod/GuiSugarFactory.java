package com.valentin4311.candycraftmod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSugarFactory extends GuiContainer {
   public static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/gui/gui_sugar.png");
   public static final ResourceLocation texture2 = new ResourceLocation("candycraftmod:textures/gui/gui_advancedsugar.png");
   private TileEntitySugarFactory furnaceInventory;

   public GuiSugarFactory(InventoryPlayer playerInv, TileEntitySugarFactory tile) {
      super(new ContainerSugarFactory(playerInv, tile));
      this.furnaceInventory = tile;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.renderHoveredToolTip(mouseX, mouseY);
   }

   @Override
   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      String s = this.furnaceInventory.advancedMode ? I18n.format("Gui.AdvancedSugarFactory") : I18n.format("Gui.SugarFactory");
      this.drawCenteredString(this.fontRenderer, s, this.xSize / 2, 12, 16777215);
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(this.furnaceInventory.advancedMode ? texture2 : texture);
      this.drawTexturedModalRect(this.width / 2 - 87, this.height / 2 - 57, 0, 0, 174, 114);
      this.drawTexturedModalRect(this.width / 2 - 87 + 27, this.height / 2 - 57 + 9, 0, 114, this.furnaceInventory.currentTime / 2, 12);
   }
}
