package com.valentin4311.candycraftmod;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Property.Type;

public class CandyConfiguration extends Configuration {
   public CandyConfiguration(File file) {
      super(file);
   }

   public void setLanguage(String category, String key, String value) {
      Property prop = this.get(category, key, "ENG", (String)null, Type.BOOLEAN);
      prop.set(value);
   }

   public void setAsked(String category, String key) {
      Property prop = this.get(category, key, "ENG", (String)null, Type.BOOLEAN);
      prop.set(false);
   }
}
