# mod-porting

Ports of 1.7.10 mods to Minecraft 1.12.2 (Forge).

| Mod | Status |
|-----|--------|
| `OooCraft-1.12.2/` | All classes ported; **uncompiled** (see its `PORT_NOTES.md`) |
| CandyCraft | not yet ported |
| CustomNPCs | not yet ported |

The sources were written without access to a compiler, so expect some compile errors on first build.
Forge's Maven no longer serves the 1.12.2 `-userdev.jar` that ForgeGradle 2/3 need, so building needs a
maintained 1.12.2 toolchain (e.g. RetroFuturaGradle or Cleanroom); copy `src/` into that template.
