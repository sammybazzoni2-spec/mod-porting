package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterVoid extends Teleporter {
   public TeleporterVoid(WorldServer par1WorldServer) {
      super(par1WorldServer);
   }

   @Override
   public void placeInPortal(Entity par1Entity, float rotationYaw) {
   }

   public void placeEntity(World world, Entity entity, float yaw) {
   }

   @Override
   public boolean placeInExistingPortal(Entity par1Entity, float rotationYaw) {
      return true;
   }
}
