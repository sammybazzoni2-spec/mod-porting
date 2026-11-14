package com.valentin4311.candycraftmod;

import java.io.File;

public class CandyCraftConfig {
   CandyConfiguration config;
   public static boolean canGenerateDungeon = true;
   public static boolean canOpenPortalWithLava = true;
   public static boolean canOpenPortalWithCaramel = true;
   public static boolean checkForUpdates = true;
   public static boolean generateGingerbreadVillage = true;
   public static boolean generateFloatingIsland = true;
   public static boolean allowDragons = true;
   public static boolean allowBeetleKings = true;

   public CandyCraftConfig(File confDirectory) {
      this.config = new CandyConfiguration(new File(confDirectory, "CandyCraft/ServerConfig.cfg"));
      this.config.load();
      canGenerateDungeon = this.config.get("Configuration", "canGenerateDungeons", true).getBoolean();
      canOpenPortalWithLava = this.config.get("Configuration", "canOpenPortalWithLava", true).getBoolean();
      canOpenPortalWithCaramel = this.config.get("Configuration", "canOpenPortalWithCaramel", true).getBoolean();
      checkForUpdates = this.config.get("Configuration", "checkForUpdates", true).getBoolean();
      generateGingerbreadVillage = this.config.get("Configuration", "canGenerateGingerbreadVillage", true).getBoolean();
      generateFloatingIsland = this.config.get("Configuration", "canGenerateFloatingIsland", true).getBoolean();
      allowDragons = this.config.get("Configuration", "allowDragons", true).getBoolean();
      allowBeetleKings = this.config.get("Configuration", "allowBeetleKings", true).getBoolean();
      this.config.save();
   }
}
