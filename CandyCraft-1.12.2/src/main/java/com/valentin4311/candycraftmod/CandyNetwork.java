package com.valentin4311.candycraftmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/** Replaces the 1.7.10 "%CandyCraft-...%" chat-message hack used for mount controls. */
public class CandyNetwork {
   public static final int JUMP = 0;
   public static final int LOCK = 1;
   public static final int POWER = 2;
   public static SimpleNetworkWrapper channel;

   public static void init() {
      channel = NetworkRegistry.INSTANCE.newSimpleChannel("candycraftmod");
      channel.registerMessage(MountHandler.class, MountMessage.class, 0, Side.SERVER);
   }

   public static class MountMessage implements IMessage {
      public int action;

      public MountMessage() {
      }

      public MountMessage(int action) {
         this.action = action;
      }

      @Override
      public void fromBytes(ByteBuf buf) {
         this.action = buf.readInt();
      }

      @Override
      public void toBytes(ByteBuf buf) {
         buf.writeInt(this.action);
      }
   }

   public static class MountHandler implements IMessageHandler<MountMessage, IMessage> {
      @Override
      public IMessage onMessage(final MountMessage message, final MessageContext ctx) {
         final EntityPlayerMP player = ctx.getServerHandler().player;
         player.getServerWorld().addScheduledTask(new Runnable() {
            public void run() {
               handle(player, message.action);
            }
         });
         return null;
      }
   }

   static void handle(EntityPlayerMP player, int action) {
      Entity ride = player.getRidingEntity();
      if (ride == null) {
         return;
      }
      if (action == JUMP && ride instanceof IEntityPowerMount && ride instanceof EntityLiving) {
         IEntityPowerMount mount = (IEntityPowerMount)ride;
         EntityLiving living = (EntityLiving)ride;
         if (mount.getPower() >= mount.maxPower() / 10 && living.onGround) {
            mount.setPower(mount.getPower() - mount.maxPower() / 10);
            living.getJumpHelper().setJumping();
         }
      } else if (action == LOCK && ride instanceof IEntityLockable) {
         IEntityLockable mount = (IEntityLockable)ride;
         mount.setLocked(!mount.isLocked());
      } else if (action == POWER && ride instanceof IEntityPowerMount) {
         IEntityPowerMount mount = (IEntityPowerMount)ride;
         if (mount.getPower() >= mount.powerUsed()) {
            mount.unleashPower();
         }
      }
   }
}
