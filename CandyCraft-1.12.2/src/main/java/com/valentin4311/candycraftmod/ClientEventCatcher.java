package com.valentin4311.candycraftmod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Client-only event handler (registered by ClientProxy). */
@SideOnly(Side.CLIENT)
public class ClientEventCatcher {
   private GuiBoss boss() {
      return CandyCraft.clientTicker.getBossGui();
   }

   @SubscribeEvent
   public void onKeyInput(InputEvent.KeyInputEvent event) {
      Minecraft mc = Minecraft.getMinecraft();
      if (mc.player == null) {
         return;
      }
      Entity ride = mc.player.getRidingEntity();
      boolean jumpPressed = mc.gameSettings.keyBindJump.isPressed();
      if (jumpPressed && ride instanceof IEntityPowerMount && !(ride instanceof IEntityLockable) && ((IEntityPowerMount)ride).getPower() >= ((IEntityPowerMount)ride).maxPower() / 10 && ride.onGround) {
         CandyNetwork.channel.sendToServer(new CandyNetwork.MountMessage(CandyNetwork.JUMP));
      }
      if (jumpPressed && ride instanceof IEntityLockable) {
         CandyNetwork.channel.sendToServer(new CandyNetwork.MountMessage(CandyNetwork.LOCK));
      }
      if (ClientProxy.unleashMountPower.isPressed() && ride instanceof IEntityPowerMount && ((IEntityPowerMount)ride).getPower() >= ((IEntityPowerMount)ride).powerUsed()) {
         CandyNetwork.channel.sendToServer(new CandyNetwork.MountMessage(CandyNetwork.POWER));
      }
   }

   @SubscribeEvent
   public void onHit(AttackEntityEvent event) {
      if (!event.getEntityPlayer().world.isRemote) {
         return;
      }
      Entity target = event.getTarget();
      if (target instanceof ICandyBoss) {
         boss().lastHitted = (EntityLiving)target;
         boss().counter = 1500;
      }
      if (target instanceof EntityGummyBall && ((EntityGummyBall)target).beetle != null) {
         boss().lastHitted = ((EntityGummyBall)target).beetle;
         boss().counter = 1500;
      }
   }

   @SubscribeEvent
   public void onRenderTick(TickEvent.RenderTickEvent event) {
      if (event.phase == TickEvent.Phase.END && CandyCraft.clientTicker != null) {
         CandyCraft.clientTicker.onRenderTick(event.renderTickTime);
      }
   }

   /** Only fires in single player (the server thread of the integrated server). */
   @SubscribeEvent
   public void onEntityHurt(LivingHurtEvent event) {
      EntityPlayer me = Minecraft.getMinecraft().player;
      if (me == null) {
         return;
      }
      if (event.getEntity() instanceof ICandyBoss && event.getSource().isProjectile() && event.getSource().getTrueSource() != null && event.getSource().getTrueSource().getEntityId() == me.getEntityId()) {
         boss().lastHitted = (EntityLiving)event.getEntity();
         boss().counter = 1500;
      }
      if (boss().lastHitted != null && event.getEntity() instanceof ICandyBoss && event.getEntity().getEntityId() == boss().lastHitted.getEntityId()) {
         ((ICandyBoss)event.getEntity()).lastDamage(event.getAmount());
      }
   }

   @SubscribeEvent
   public void onDeath(LivingDeathEvent event) {
      if (event.getEntity() == Minecraft.getMinecraft().player) {
         boss().lastHitted = null;
      }
   }
}
