package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Item meta variants of the dynamite items: 0 plain, 1 lit (on), 2 about to explode (warn). */
@SideOnly(Side.CLIENT)
public class RenderDynamite extends RenderSnowball<Entity> {
   public final boolean glue;
   private final Item item;

   public RenderDynamite(RenderManager manager, boolean glue) {
      super(manager, glue ? CandyCraft.GlueDynamite : CandyCraft.Dynamite, Minecraft.getMinecraft().getRenderItem());
      this.glue = glue;
      this.item = glue ? CandyCraft.GlueDynamite : CandyCraft.Dynamite;
   }

   @Override
   public ItemStack getStackToRender(Entity entity) {
      int fuse = entity instanceof EntityGlueDynamite ? ((EntityGlueDynamite)entity).fuse : (entity instanceof EntityDynamite ? ((EntityDynamite)entity).fuse : 65);
      return new ItemStack(this.item, 1, fuse <= 15 ? 2 : 1);
   }
}
