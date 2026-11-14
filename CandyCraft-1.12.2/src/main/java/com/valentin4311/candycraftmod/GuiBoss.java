package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBoss extends Gui {
   public EntityLiving lastHitted;
   public int counter = 0;
   public float lastLife = 0.0F;
   private Minecraft mc = Minecraft.getMinecraft();
   private ResourceLocation gui = new ResourceLocation("candycraftmod:textures/gui/gui_boss.png");
   String[] difficulties = null;
   double animation = 0.0D;
   int tick = 0;
   boolean ascending = true;
   public static boolean renderInGUI = false;

   private String[] getDifficulties() {
      if (this.difficulties == null) {
         this.difficulties = new String[]{I18n.format("Gui.Boss.Sentry"), I18n.format("Gui.Boss.MiniBoss"), I18n.format("Gui.Boss.Boss")};
      }
      return this.difficulties;
   }

   public void drawScreen(float partialTicks) {
      this.mc.getTextureManager().bindTexture(this.gui);
      ScaledResolution scaledresolution = new ScaledResolution(this.mc);
      if (this.mc.player != null && this.mc.player.getRidingEntity() instanceof IEntityPowerMount) {
         renderInGUI = true;
         IEntityPowerMount mount = (IEntityPowerMount)this.mc.player.getRidingEntity();
         double d = (double)mount.getPower();
         int percent = MathHelper.floor(d / (double)mount.maxPower() * 42.0D);
         this.drawTexturedModalRect(scaledresolution.getScaledWidth() - 50, 0, 0, 84, 50, 50);
         this.drawTexturedModalRect(scaledresolution.getScaledWidth() - 46, 44, 0, 134, percent, 2);
         this.drawCenteredString(this.mc.fontRenderer, I18n.format("Gui.Energy"), scaledresolution.getScaledWidth() - 25, 35, 16777215);
         if (mount instanceof IEntityLockable) {
            IEntityLockable lockable = (IEntityLockable)mount;
            String display = I18n.format(lockable.isLocked() ? "Gui.UnlockHeight" : "Gui.LockHeight").replaceAll("-key-", GameSettings.getKeyDisplayString(this.mc.gameSettings.keyBindJump.getKeyCode()));
            this.drawString(this.mc.fontRenderer, display, scaledresolution.getScaledWidth() - this.mc.fontRenderer.getStringWidth(display) - 2, 52, 16777215);
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         drawEntityOnScreen(scaledresolution.getScaledWidth() - 25, 30, 10.0F, 45.0F, -45.0F, (EntityLivingBase)this.mc.player.getRidingEntity());
      }

      this.mc.getTextureManager().bindTexture(this.gui);
      if (this.counter > 0) {
         --this.counter;
      } else {
         this.lastHitted = null;
      }

      if (this.lastHitted != null && !this.lastHitted.isDead && this.lastHitted instanceof ICandyBoss && this.counter > 0) {
         ++this.tick;
         if (this.animation >= 0.0D && !this.ascending) {
            this.animation -= 0.3D;
         }

         if (this.animation >= 1.0D && this.ascending) {
            this.animation += 0.3D;
         }

         if (this.animation >= 4.0D) {
            this.ascending = false;
         }

         if (this.animation <= 0.0D) {
            this.ascending = true;
         }

         if (this.animation <= 0.0D && this.tick % 800 == 0 && this.ascending) {
            this.animation = 1.0D;
         }

         double[] color = ((ICandyBoss)this.lastHitted).getBarColor();
         int i = scaledresolution.getScaledWidth();
         int j = i / 2;
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(j - 128, 27, 0, 0, 256, 14);
         int stats = ((ICandyBoss)this.lastHitted).bossStatue();
         double d = (double)this.lastHitted.getHealth();
         double percent = d / (double)this.lastHitted.getMaxHealth() * 256.0D;
         double y = (double)this.lastLife / (double)this.lastHitted.getMaxHealth() * 256.0D;
         int z = (int)((double)((int)percent) + y);
         if (z > 256) {
            z = 256;
         }

         this.drawTexturedModalRect(j - 128, 27, 0, 14, z, 14);
         GlStateManager.color((float)color[0], (float)color[1], (float)color[2], 1.0F);
         this.drawTexturedModalRect(j - 128, 27, 0, 70, (int)percent, 14);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(j - 32, 6, (int)this.animation * 64, 200, 64, 56);
         String[] diff = this.getDifficulties();
         this.drawCenteredString(this.mc.fontRenderer, this.lastHitted.getName(), j, 7, 16777215);
         this.drawCenteredString(this.mc.fontRenderer, stats == 0 ? diff[1] : (stats == 1 ? diff[0] : diff[2]), j, 53, 16777215);
         if (this.lastLife > 0.0F) {
            this.lastLife -= 0.7F;
         }
      } else {
         this.tick = 0;
         this.lastLife = 0.0F;
      }

      renderInGUI = false;
   }

   public static void drawEntityOnScreen(int x, int y, float scale, float f, float g, EntityLivingBase entity) {
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)x, (float)y, 50.0F);
      GlStateManager.scale(-scale, scale, scale);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan((double)(g / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate(0.0F, entity.height * 0.0F, 0.0F);
      RenderManager rm = Minecraft.getMinecraft().getRenderManager();
      rm.setPlayerViewY(180.0F);
      rm.setRenderShadow(false);
      rm.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
      rm.setRenderShadow(true);
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }
}
