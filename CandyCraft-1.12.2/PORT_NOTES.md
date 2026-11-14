# CandyCraft 1.7.10 -> 1.12.2 port notes (agent A: infra + items)

- Achievements were removed (not in 1.12). All CandyCraft.<achievement> fields and trigger code are gone. TODO(port): optional advancements JSON.
- Update checker (ThreadCheckMaj) disabled (dead URL); class kept as no-op.
- Recipes are registered in Java (CandyRecipes, Register<IRecipe>) instead of JSON; vanilla smelting in CandyRecipes.registerSmelting. Custom sugar-furnace recipes: CandyFurnaceRecipe.
- Mount controls (jump/lock/power) use SimpleNetworkWrapper (CandyNetwork) instead of the chat-message hack.
- Boss-bar hooks for LivingHurt/Death are only reliable in single player (integrated server thread). TODO(port): packet-based boss sync.
- Registry names: lowercase of the original unlocalized name (item I0 -> candycraftmod:i0), unlocalized names (and lang keys) unchanged.
- Spawn eggs: ItemCandyMonsterPlacer is NOT registered; eggs come from CandyEntityRegistry (.egg()). Entity ids are constants EntityXxxID = 1..29 in CandyCraft.
- Sounds: CandyStepSound.getOrCreate registers dig.jelly, step.jelly and records.CD-1..4 (via CandyCraft.registerSounds). CandySounds (agent D) must not register those names again.
- Grenadine bucket: fluid container registry dropped; FillBucketEvent handler fills it from Grenadine blocks. TODO(port): proper IFluidHandlerItem.
- GummyBall / Dynamite / GlueDynamite: metadata selects the model for renderers (GummyBall 0 normal,1 flame,2 chewing gum; Dynamite 0,1 lit,2 warning). Bow/crossbow/dynamite in-hand states use item property overrides (candycraftmod:pull, candycraftmod:fuse).
- Crossbow previously consumed honey arrows while requiring bolts; now consumes bolts.
- Assumed cross-agent APIs: WorldGenFloatingIsland.generate(World,Random,BlockPos,int) and finishGeneration(World,BlockPos); TileEntityTeleporter public fields x,y,z,oX,oY,oZ,dim,player,dungeonID; EntityCandyArrow(World,EntityLivingBase,float) extends EntityArrow with slow/setBolt; EntityThrowable ctors must launch themselves; BlockCandySapling.markOrGrowMarked(World,BlockPos,Random); BlockLollipopPlant.fertilize(World,BlockPos); Container/Gui ctors with BlockPos for the workbench; CandyEntityRegistry/CandyWorldRegistry/CandyBlockRegistry/CandySounds as per brief.
- CandyChestHelper (1.7 block render handler) was not ported (obsolete).
