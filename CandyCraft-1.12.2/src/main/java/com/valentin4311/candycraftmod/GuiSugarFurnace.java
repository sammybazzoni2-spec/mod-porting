package com.valentin4311.candycraftmod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSugarFurnace extends GuiContainer {
   private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/gui/gui_sugarfurnace.png");
   private TileEntitySugarFurnace furnaceInventory;

   public GuiSugarFurnace(InventoryPlayer playerInv, TileEntitySugarFurnace tile) {
      super(new ContainerSugarFurnace(playerInv, tile));
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
      String s = I18n.format("Gui.SugarFurnace");
      this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 7829367);
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(texture);
      int k = (this.width - this.xSize) / 2;
      int l = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
      if (this.furnaceInventory.isBurning()) {
         int i1 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
         this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
      }
      int j1 = this.furnaceInventory.getCookProgressScaled(24);
      this.drawTexturedModalRect(k + 79, l + 34, 176, 14, j1 + 1, 16);
   }
}
