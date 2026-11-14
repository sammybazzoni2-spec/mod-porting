package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityAlchemyRenderer extends TileEntitySpecialRenderer<TileEntityAlchemy> {
   public static TileEntityAlchemyRenderer instance = new TileEntityAlchemyRenderer();
   private ModelAlchemy model = new ModelAlchemy();
   private static final ResourceLocation texture = new ResourceLocation("candycraftmod:textures/blocks/alchemytable.png");

   public TileEntityAlchemyRenderer() {
      instance = this;
   }

   @Override
   public void render(TileEntityAlchemy table, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      GlStateManager.pushMatrix();
      GlStateManager.enableRescaleNormal();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.translate((float)x, (float)y + 2.0F, (float)z + 1.0F);
      GlStateManager.scale(1.0F, -1.0F, -1.0F);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      GlStateManager.disableCull();
      if (table.isTopFilled()) {
         this.model.Shape10.showModel = true;
      } else {
         this.model.Shape10.showModel = false;
      }

      if (table.getLiquid() > 0) {
         this.model.Shape11.showModel = true;
         this.model.Shape11.offsetY = -((float)table.getLiquid()) / 16.0F;
         this.model.Shape12 = new ModelRenderer(this.model, 72, 43);
         this.model.Shape12.addBox(0.0F, 0.0F, 0.0F, 14, table.getLiquid(), 14);
         this.model.Shape12.setRotationPoint(-7.0F, 21.0F - (float)table.getLiquid(), -7.0F);
         this.model.Shape12.setTextureSize(128, 64);
         this.model.Shape12.mirror = true;
         this.model.Shape12.showModel = true;
      } else {
         this.model.Shape11.showModel = false;
         this.model.Shape12.showModel = false;
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.translate(0.5F, 0.5F, 0.5F);
      this.bindTexture(texture);
      this.model.renderAll();
      GlStateManager.disableBlend();
      GlStateManager.enableCull();
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }
}
