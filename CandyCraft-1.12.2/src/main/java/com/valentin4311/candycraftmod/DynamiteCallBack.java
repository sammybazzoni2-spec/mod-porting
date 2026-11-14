package com.valentin4311.candycraftmod;

import java.util.Random;
import net.minecraft.entity.Entity;

public class DynamiteCallBack {
   public Entity entity;
   public int fuseTime;
   public Random rand = new Random();

   public DynamiteCallBack(Entity e, int i) {
      this.entity = e;
      this.fuseTime = i;
   }

   public void reduce(int index) {
      --this.fuseTime;
      if (this.fuseTime <= 0 && this.entity != null && !this.entity.world.isRemote) {
         this.explode(index);
      }
   }

   public void explode(int index) {
      boolean grief = this.entity.world.getGameRules().getBoolean("mobGriefing");
      this.entity.world.createExplosion((Entity)null, this.entity.posX, this.entity.posY, this.entity.posZ, 3.0F, grief);
      ServerTick.dynamiteCallBack.remove(index);
   }
}
