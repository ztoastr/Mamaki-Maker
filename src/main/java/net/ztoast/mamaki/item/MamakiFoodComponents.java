package net.ztoast.mamaki.item;

import net.minecraft.component.type.FoodComponent;

public class MamakiFoodComponents {
    public static final FoodComponent SWEET_POTATO = new FoodComponent.Builder()
        .nutrition(1).saturationModifier(0.3f).build();
    
    public static final FoodComponent BAKED_SWEET_POTATO = new FoodComponent.Builder()
        .nutrition(5).saturationModifier(0.6f).build();
}