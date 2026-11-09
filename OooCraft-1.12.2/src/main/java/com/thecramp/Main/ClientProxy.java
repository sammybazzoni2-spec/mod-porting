package com.thecramp.Main;

import com.thecramp.entity.EntityAdrian;
import com.thecramp.entity.EntityDarren;
import com.thecramp.entity.EntityDemon;
import com.thecramp.entity.EntityFinnParadox;
import com.thecramp.entity.EntityGunter;
import com.thecramp.entity.EntityJamesBaxter;
import com.thecramp.entity.EntityLemongrab;
import com.thecramp.entity.EntityLich;
import com.thecramp.entity.EntityMagicMan;
import com.thecramp.entity.RenderAdrian;
import com.thecramp.entity.RenderDarren;
import com.thecramp.entity.RenderDemon;
import com.thecramp.entity.RenderFinnParadox;
import com.thecramp.entity.RenderGunter;
import com.thecramp.entity.RenderJamesBaxter;
import com.thecramp.entity.RenderLemongrab;
import com.thecramp.entity.RenderLich;
import com.thecramp.entity.RenderMagicMan;
import com.thecramp.mob.AdrianBoy;
import com.thecramp.mob.Gunter;
import com.thecramp.mob.LemonGrab;
import com.thecramp.mob.ModelJamesBaxter;
import com.thecramp.mob.ModelLich;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {
   @Override
   public void registerRenderInfo() {
   }

   @Override
   public void registerRenderers() {
      RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, manager -> new RenderDemon(manager, new ModelBiped(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityDarren.class, manager -> new RenderDarren(manager, new ModelBiped(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityMagicMan.class, manager -> new RenderMagicMan(manager, new ModelBiped(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityGunter.class, manager -> new RenderGunter(manager, new Gunter(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityJamesBaxter.class, manager -> new RenderJamesBaxter(manager, new ModelJamesBaxter(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityLich.class, manager -> new RenderLich(manager, new ModelLich(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityLemongrab.class, manager -> new RenderLemongrab(manager, new LemonGrab(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityAdrian.class, manager -> new RenderAdrian(manager, new AdrianBoy(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityFinnParadox.class, manager -> new RenderFinnParadox(manager, new ModelBiped(), 0.5F));
   }
}
