package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Item meta variants of GummyBall: 0 normal, 1 flame (power 2), 2 chewing gum (power 3). */
@SideOnly(Side.CLIENT)
public class RenderGummyBall extends RenderSnowball<EntityGummyBall> {
   private final Item item;

   public RenderGummyBall(RenderManager manager, Item item) {
      super(manager, item, Minecraft.getMinecraft().getRenderItem());
      this.item = item;
   }

   @Override
   public ItemStack getStackToRender(EntityGummyBall ball) {
      int p = ball.getPowerful();
      return new ItemStack(this.item, 1, p == 2 ? 1 : (p == 3 ? 2 : 0));
   }
}
