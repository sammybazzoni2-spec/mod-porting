# OooCraft 1.12.2 port notes

Ported from the decompiled 1.7.10 sources (com.thecramp.*) to Forge 1.12.2 (14.23.5.2860, MCP stable_39).
**Not compiled**: the build environment couldn't reach Forge's Maven, so expect a few compile errors to fix.

- All 76 original classes ported; added `Main/ModelHandler` (item/block model registration) and `lib/ModSounds` (SoundEvents).
- Blocks, items, entities, biome and sound registration moved to RegistryEvent handlers in `MainRegistry`.
- Blockstates, block/item models, recipes, sounds.json and lang (`en_us.lang`) are generated JSON under `src/main/resources/assets/ooocraft`.
- Original `mcmod.info` was an "examplemod" placeholder; the real modid `ooocraft` is used.

## Known gaps
- `WorldTypeBiomeDimension1`: verify the 1.12.2 `WorldType.getBiomeLayer` signature (TODO(port)).
- `EntityJamesBaxter`: `setAvoidsWater` has no direct 1.12 equivalent, so it was dropped.
- Dimension/teleporter code (`TeleporterDimension1`, `Dimension1Portal`) is the most likely to need runtime testing.

## Build
`gradle setupDecompWorkspace` then `gradle build` (Java 8, ForgeGradle 2.3). The jar lands in `build/libs`.
