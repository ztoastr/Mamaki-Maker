package net.ztoast.mamaki.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.MamakiMaker;
import net.ztoast.mamaki.block.MamakiBlocks;

public class MamakiItemGroups {
    public static final ItemGroup LOVE_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
        Identifier.of(MamakiMaker.MOD_ID, "mamaki_items"),
        FabricItemGroup.builder().icon(() -> new ItemStack(MamakiItems.KISS))
            .displayName(Text.translatable("itemgroup.mamaki.mamaki_items"))
            .entries((displayContext, entries) -> {
                entries.add(MamakiItems.KISS);
                entries.add(MamakiBlocks.LOVE_BLOCK);
                entries.add(MamakiItems.SWEET_POTATO);
                entries.add(MamakiItems.BAKED_SWEET_POTATO);
            }).build());

    public static void registerItemGroups() {
        MamakiMaker.LOGGER.info("Registering Item Groups for" + MamakiMaker.MOD_ID);
    }
}
