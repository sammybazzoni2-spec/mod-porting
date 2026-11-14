package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPingouin extends RenderLiving<EntityPingouin> {
   private static final ResourceLocation[] texture = new ResourceLocation[]{new ResourceLocation("candycraftmod:textures/entity/pingouin0.png"), new ResourceLocation("candycraftmod:textures/entity/pingouin1.png"), new ResourceLocation("candycraftmod:textures/entity/pingouin2.png")};

   public RenderPingouin(RenderManager manager, ModelBase model, float shadow) {
      super(manager, model, shadow);
   }

   @Override
   public void doRender(EntityPingouin entity, double x, double y, double z, float entityYaw, float partialTicks) {
      ((ModelPingouin)this.mainModel).Shape8.isHidden = !entity.isSuper();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   @Override
   protected void preRenderCallback(EntityPingouin entity, float partialTickTime) {
      if (entity.isChild()) {
         GlStateManager.scale(0.5F, 0.5F, 0.5F);
      }
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityPingouin entity) {
      int c = entity.getColor();
      return texture[Math.max(0, Math.min(texture.length - 1, c))];
   }
}
