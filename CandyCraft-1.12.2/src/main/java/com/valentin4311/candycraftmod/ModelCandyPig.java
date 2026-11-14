package com.valentin4311.candycraftmod;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRenderer;

public class ModelCandyPig extends ModelPig {
   public ModelCandyPig() {
      this(0.0F);
   }

   public ModelCandyPig(float par2) {
      super(par2);
      this.leg1 = new ModelRenderer(this, 0, 16);
      this.leg1.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
      this.leg1.setRotationPoint(-3.0F, 18.0F, 7.0F);
      this.leg2 = new ModelRenderer(this, 12, 22);
      this.leg2.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
      this.leg2.setRotationPoint(3.0F, 18.0F, 7.0F);
      this.leg3 = new ModelRenderer(this, 0, 16);
      this.leg3.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
      this.leg3.setRotationPoint(-3.0F, 18.0F, -5.0F);
      this.leg4 = new ModelRenderer(this, 12, 22);
      this.leg4.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 4, par2);
      this.leg4.setRotationPoint(3.0F, 18.0F, -5.0F);
   }
}

