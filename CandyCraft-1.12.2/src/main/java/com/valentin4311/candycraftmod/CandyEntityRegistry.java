package com.valentin4311.candycraftmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Registers every CandyCraft entity (ids as in the 1.7.10 version, see CandyCraft.EntityXxxID) together with
 * the spawn eggs. Natural spawns are declared by the biome classes (spawnable lists).
 */
public class CandyEntityRegistry {
   public static void register(RegistryEvent.Register<EntityEntry> event) {
      IForgeRegistry<EntityEntry> registry = event.getRegistry();
      registry.register(EntityEntryBuilder.create().entity(EntityCandyPig.class).id(new ResourceLocation("candycraftmod", "candypig"), CandyCraft.EntityCandyPigID).name("CandyPig").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xFB5757).build());
      registry.register(EntityEntryBuilder.create().entity(EntityCandyCreeper.class).id(new ResourceLocation("candycraftmod", "candycreeper"), CandyCraft.EntityCandyCreeperID).name("CandyCreeper").tracker(80, 3, true)
         .egg(0xF1C3C3, 0x777777).build());
      registry.register(EntityEntryBuilder.create().entity(EntitySuguard.class).id(new ResourceLocation("candycraftmod", "suguard"), CandyCraft.EntitySuguardID).name("Suguard").tracker(80, 3, true)
         .egg(0xF1C3C3, 0x8E0082).build());
      registry.register(EntityEntryBuilder.create().entity(EntityMageSuguard.class).id(new ResourceLocation("candycraftmod", "suguardmage"), CandyCraft.EntitySuguardMageID).name("SuguardMage").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xAE8FD7).build());
      registry.register(EntityEntryBuilder.create().entity(EntityCandyWolf.class).id(new ResourceLocation("candycraftmod", "candywolf"), CandyCraft.EntityCandyWolfID).name("CandyWolf").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xDDDDDD).build());
      registry.register(EntityEntryBuilder.create().entity(EntityCandySlime.class).id(new ResourceLocation("candycraftmod", "queenslime"), CandyCraft.EntityCandySlimeID).name("QueenSlime").tracker(80, 3, true)
         .egg(0xFF7373, 0xCF00EF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityBunny.class).id(new ResourceLocation("candycraftmod", "gummybunny"), CandyCraft.EntityBunnyID).name("GummyBunny").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xEEFF33).build());
      registry.register(EntityEntryBuilder.create().entity(EntityBee.class).id(new ResourceLocation("candycraftmod", "caramelbee"), CandyCraft.EntityBeeID).name("CaramelBee").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xFE7F01).build());
      registry.register(EntityEntryBuilder.create().entity(EntityGingerBreadMan.class).id(new ResourceLocation("candycraftmod", "gingerbreadman"), CandyCraft.EntityGingerBreadID).name("GingerBreadMan").tracker(80, 3, true)
         .egg(0xF1C3C3, 0x61380B).build());
      registry.register(EntityEntryBuilder.create().entity(EntityCandyArrow.class).id(new ResourceLocation("candycraftmod", "candyarrow"), CandyCraft.EntityCandyArrowID).name("CandyArrow").tracker(64, 60, true).build());
      registry.register(EntityEntryBuilder.create().entity(EntityGummyBall.class).id(new ResourceLocation("candycraftmod", "gummyball"), CandyCraft.EntityGummyBallID).name("GummyBall").tracker(64, 10, true).build());
      registry.register(EntityEntryBuilder.create().entity(EntityBossSuguard.class).id(new ResourceLocation("candycraftmod", "bosssuguard"), CandyCraft.EntitySuguardBossID).name("BossSuguard").tracker(80, 3, true)
         .egg(0xFF7373, 0xDFDFDF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityCottonCandySpider.class).id(new ResourceLocation("candycraftmod", "cottoncandyspider"), CandyCraft.EntitySpiderID).name("CottonCandySpider").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xA00000).build());
      registry.register(EntityEntryBuilder.create().entity(EntityFish.class).id(new ResourceLocation("candycraftmod", "candyfish"), CandyCraft.EntityFishID).name("CandyFish").tracker(80, 3, true)
         .egg(0xF1C3C3, 0x3A01DF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityNessie.class).id(new ResourceLocation("candycraftmod", "nessie"), CandyCraft.EntityNessieID).name("Nessie").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xA9E2F3).build());
      registry.register(EntityEntryBuilder.create().entity(EntityWaffleSheep.class).id(new ResourceLocation("candycraftmod", "wafflesheep"), CandyCraft.EntityWaffleSheepID).name("WaffleSheep").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xFFC000).build());
      registry.register(EntityEntryBuilder.create().entity(EntitySprinterSlime.class).id(new ResourceLocation("candycraftmod", "sprinterslime"), CandyCraft.EntitySprinterSlimeID).name("SprinterSlime").tracker(80, 3, true)
         .egg(0x555555, 0xFFFF00).build());
      registry.register(EntityEntryBuilder.create().entity(EntityKamikazeSlime.class).id(new ResourceLocation("candycraftmod", "kamikazeslime"), CandyCraft.EntityKamikazeSlimeID).name("KamikazeSlime").tracker(80, 3, true)
         .egg(0x555555, 0xFF0000).build());
      registry.register(EntityEntryBuilder.create().entity(EntityTornadoSlime.class).id(new ResourceLocation("candycraftmod", "tornadoslime"), CandyCraft.EntityTornadoSlimeID).name("TornadoSlime").tracker(80, 3, true)
         .egg(0x555555, 0x00FFFF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityPEZSlime.class).id(new ResourceLocation("candycraftmod", "pezslime"), CandyCraft.EntityPEZSlimeID).name("PEZSlime").tracker(80, 3, true)
         .egg(0x9166FF, 0xFFFFFF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityKingSlime.class).id(new ResourceLocation("candycraftmod", "kingslime"), CandyCraft.EntityKingSlimeID).name("KingSlime").tracker(80, 3, true)
         .egg(0xB23838, 0xE37D11).build());
      registry.register(EntityEntryBuilder.create().entity(EntityDynamite.class).id(new ResourceLocation("candycraftmod", "candydynamite"), CandyCraft.EntityDynamiteID).name("CandyDynamite").tracker(64, 10, true).build());
      registry.register(EntityEntryBuilder.create().entity(EntityGlueDynamite.class).id(new ResourceLocation("candycraftmod", "candygluedynamite"), CandyCraft.EntityGlueDynamiteID).name("CandyGlueDynamite").tracker(64, 10, true).build());
      registry.register(EntityEntryBuilder.create().entity(EntityNougatGolem.class).id(new ResourceLocation("candycraftmod", "entitynougatgolem"), CandyCraft.EntityNougatGolemID).name("EntityNougatGolem").tracker(80, 3, true).build());
      registry.register(EntityEntryBuilder.create().entity(EntityBeetle.class).id(new ResourceLocation("candycraftmod", "entitybeetle"), CandyCraft.EntityBeetleID).name("EntityBeetle").tracker(80, 3, true)
         .egg(0xF1C3C3, 0x250066).build());
      registry.register(EntityEntryBuilder.create().entity(EntityBossBeetle.class).id(new ResourceLocation("candycraftmod", "entitybossbeetle"), CandyCraft.EntityBossBeetleID).name("EntityBossBeetle").tracker(80, 3, true)
         .egg(0xFF7373, 0x1C1C1C).build());
      registry.register(EntityEntryBuilder.create().entity(EntityPingouin.class).id(new ResourceLocation("candycraftmod", "entitypingouin"), CandyCraft.EntityPingouinID).name("EntityPingouin").tracker(80, 3, true)
         .egg(0xF1C3C3, 0xFFFFFF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityDragon.class).id(new ResourceLocation("candycraftmod", "entitydragon"), CandyCraft.EntityDragonID).name("EntityDragon").tracker(80, 3, true)
         .egg(0x8DC444, 0xA4EDFF).build());
      registry.register(EntityEntryBuilder.create().entity(EntityKingBeetle.class).id(new ResourceLocation("candycraftmod", "entitykingbeetle"), CandyCraft.EntityKingBeetleID).name("EntityKingBeetle").tracker(80, 3, true)
         .egg(0x8DC444, 0xA500B3).build());
   }
}
