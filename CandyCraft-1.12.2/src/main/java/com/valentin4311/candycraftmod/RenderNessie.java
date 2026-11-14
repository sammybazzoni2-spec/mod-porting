package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNessie extends RenderLiving<EntityNessie> {
   private static ModelNessie modelOne = new ModelNessie(0.0F);
   private static ModelNessie2 modelTwo = new ModelNessie2(0.0F);
   private static final ResourceLocation[] img = new ResourceLocation[]{new ResourceLocation("candycraftmod:textures/entity/nessie0.png"), new ResourceLocation("candycraftmod:textures/entity/nessie1.png"), new ResourceLocation("candycraftmod:textures/entity/nessie2.png"), new ResourceLocation("candycraftmod:textures/entity/nessie3.png"), new ResourceLocation("candycraftmod:textures/entity/nessie4.png"), new ResourceLocation("candycraftmod:textures/entity/nessie5.png"), new ResourceLocation("candycraftmod:textures/entity/nessie6.png")};
   private static final ResourceLocation saddle = new ResourceLocation("candycraftmod:textures/entity/nessiesaddle.png");

   public RenderNessie(RenderManager manager) {
      super(manager, modelOne, 0.5F);
      final ModelNessie saddleModel = new ModelNessie(0.2F);
      this.addLayer(new LayerRenderer<EntityNessie>() {
         @Override
         public void doRenderLayer(EntityNessie nessie, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (nessie.getSaddled()) {
               RenderNessie.this.bindTexture(saddle);
               saddleModel.setModelAttributes(RenderNessie.this.getMainModel());
               saddleModel.render(nessie, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
         }

         @Override
         public boolean shouldCombineTextures() {
            return false;
         }
      });
   }

   @Override
   protected void preRenderCallback(EntityNessie entity, float partialTickTime) {
      if (entity.isChild()) {
         GlStateManager.scale(0.5F, 0.5F, 0.5F);
      }
   }

   @Override
   public void doRender(EntityNessie entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      Minecraft mc = Minecraft.getMinecraft();
      if (!GuiBoss.renderInGUI && entity.isRidingOrBeingRiddenBy(mc.player) && mc.gameSettings.thirdPersonView == 0) {
         this.mainModel = modelTwo;
      } else {
         this.mainModel = modelOne;
      }
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityNessie entity) {
      int t = entity.getType();
      return t >= 0 && t < img.length ? img[t] : img[0];
   }
}
