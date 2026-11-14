package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSlime;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Client-only registration of the entity renderers (called from ClientProxy.preInit).
 * Block renderers (chest, pudding, alchemy table) and the crossbow item renderer of the 1.7.10 version are
 * replaced by JSON models / tile entity renderers registered by CandyBlockClient.
 */
@SideOnly(Side.CLIENT)
public class CandyRenderRegistry {
   public static void registerAll() {
      RenderingRegistry.registerEntityRenderingHandler(EntityCandyPig.class, m -> new RenderCandyPig(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityCandyCreeper.class, m -> new RenderCandyCreeper(m));
      RenderingRegistry.registerEntityRenderingHandler(EntitySuguard.class, m -> new RenderSuguard(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityMageSuguard.class, m -> new RenderMageSuguard(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityCandyWolf.class, m -> new RenderCandyWolf(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityCandySlime.class, m -> new RenderCandySlime(m, new ModelSlime(16), new ModelSlime(0), 0.25F));
      RenderingRegistry.registerEntityRenderingHandler(EntityBunny.class, m -> new RenderBunny(m, new ModelBunny(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, m -> new RenderBee(m, new ModelBee(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityGingerBreadMan.class, m -> new RenderGingerBreadMan(m, new ModelBiped(0.0F), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityCandyArrow.class, m -> new RenderCandyArrow(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityGummyBall.class, m -> new RenderGummyBall(m, CandyCraft.GummyBall));
      RenderingRegistry.registerEntityRenderingHandler(EntityBossSuguard.class, m -> new RenderSuguardeBoss(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityCottonCandySpider.class, m -> new RenderCottonSpider(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityFish.class, m -> new RenderEntityFish(m, new ModelFish(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityNessie.class, m -> new RenderNessie(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityWaffleSheep.class, m -> new RenderWaffleSheep(m, new ModelWaffleSheep2(), new ModelWaffleSheep(), 0.7F));
      RenderingRegistry.registerEntityRenderingHandler(EntitySprinterSlime.class, m -> new RenderSprinter(m, new ModelSlime(16), new ModelSlime(0), 0.25F));
      RenderingRegistry.registerEntityRenderingHandler(EntityKamikazeSlime.class, m -> new RenderKamikaze(m, new ModelSlime(16), new ModelSlime(0), 0.25F));
      RenderingRegistry.registerEntityRenderingHandler(EntityTornadoSlime.class, m -> new RenderTornado(m, new ModelSlime(16), new ModelSlime(0), 0.25F));
      RenderingRegistry.registerEntityRenderingHandler(EntityPEZSlime.class, m -> new RenderPEZSlime(m, new ModelSlime(16), new ModelSlime(0), 0.25F));
      RenderingRegistry.registerEntityRenderingHandler(EntityKingSlime.class, m -> new RenderKingSlime(m, new ModelSlime(16), new ModelSlime(0), 0.25F));
      RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, m -> new RenderDynamite(m, false));
      RenderingRegistry.registerEntityRenderingHandler(EntityGlueDynamite.class, m -> new RenderDynamite(m, true));
      RenderingRegistry.registerEntityRenderingHandler(EntityNougatGolem.class, m -> new RenderNougatGolem(m));
      RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, m -> new RenderBeetle(m, new ModelBeetle(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityBossBeetle.class, m -> new RenderBossBeetle(m, new ModelBeetle(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityPingouin.class, m -> new RenderPingouin(m, new ModelPingouin(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityDragon.class, m -> new RenderDragon(m, new ModelDragon(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityKingBeetle.class, m -> new RenderKingBeetle(m, new ModelBeetle(), 0.5F));
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEgg.class, new RenderEgg());
   }
}
