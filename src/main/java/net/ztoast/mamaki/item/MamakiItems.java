package net.ztoast.mamaki.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.MamakiMaker;

public class MamakiItems {
    public static final Item KISS = registerItem("kiss", new Item(new Item.Settings()));
    public static final Item SWEET_POTATO = registerItem("sweet_potato", 
        new Item(new Item.Settings().food(MamakiFoodComponents.SWEET_POTATO)));
    public static final Item BAKED_SWEET_POTATO = registerItem("baked_sweet_potato", 
        new Item(new Item.Settings().food(MamakiFoodComponents.BAKED_SWEET_POTATO)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MamakiMaker.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MamakiMaker.LOGGER.info("Registering Items for " + MamakiMaker.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(KISS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(SWEET_POTATO);
            entries.add(BAKED_SWEET_POTATO);
        });
    }
}
