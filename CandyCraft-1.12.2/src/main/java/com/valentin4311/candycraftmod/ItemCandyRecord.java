package com.valentin4311.candycraftmod;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandyRecord extends ItemRecord {
   String name;
   String Sname;
   String TName;

   protected ItemCandyRecord(String recordName, SoundEvent sound, String title, String shortName, String texName) {
      super(recordName, sound);
      this.name = title;
      this.Sname = shortName;
      this.TName = texName;
   }

   public String getRecordTitle() {
      return this.name;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public String getRecordNameLocal() {
      return this.name;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
      tooltip.add(this.Sname);
   }
}
