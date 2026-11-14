package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Draws the weapon in the hand (Shape10) of the Suguard models. A null fixed item means: the entity's main hand item. */
@SideOnly(Side.CLIENT)
public class RenderSuguardLayer implements LayerRenderer<EntityLivingBase> {
   private final RenderLivingBase<?> renderer;
   private final Item fixed;
   private final float scale;

   public RenderSuguardLayer(RenderLivingBase<?> renderer, Item fixed, float scale) {
      this.renderer = renderer;
      this.fixed = fixed;
      this.scale = scale;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float s) {
      ItemStack stack = this.fixed != null ? new ItemStack(this.fixed) : entity.getHeldItemMainhand();
      if (stack.isEmpty() || !(this.renderer.getMainModel() instanceof ModelSuGuard)) {
         return;
      }
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      if (this.renderer.getMainModel().isChild) {
         GlStateManager.translate(0.0F, 0.625F, 0.0F);
         GlStateManager.rotate(-20.0F, -1.0F, 0.0F, 0.0F);
         GlStateManager.scale(0.5F, 0.5F, 0.5F);
      }
      ((ModelSuGuard)this.renderer.getMainModel()).Shape10.postRender(0.0625F);
      GlStateManager.translate(0.1025F, 0.1575F, 0.0625F);
      GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, 0.125F, -0.1F);
      GlStateManager.scale(this.scale, this.scale, this.scale);
      Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
      GlStateManager.popMatrix();
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }
}
