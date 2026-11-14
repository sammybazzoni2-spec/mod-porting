package com.valentin4311.candycraftmod;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterDungeon extends Teleporter {
   TileEntityTeleporter teleporter = null;

   public TeleporterDungeon(WorldServer par1WorldServer, TileEntityTeleporter tileentityportal) {
      super(par1WorldServer);
      this.teleporter = tileentityportal;
   }

   @Override
   public void placeInPortal(Entity par1Entity, float rotationYaw) {
      if (this.teleporter != null) {
         par1Entity.setPosition((double)this.teleporter.x + 0.5D, (double)this.teleporter.y + 0.2D, (double)this.teleporter.z + 0.5D);
      }

   }

   public void placeEntity(World world, Entity entity, float yaw) {
      this.placeInPortal(entity, yaw);
   }

   @Override
   public boolean placeInExistingPortal(Entity par1Entity, float rotationYaw) {
      return false;
   }

   @Override
   public boolean makePortal(Entity par1Entity) {
      return false;
   }

   @Override
   public void removeStalePortalLocations(long par1) {
   }
}
