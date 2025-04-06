package net.ztoast.mamaki.utill;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.MamakiMaker;

public class MamakiTags {
    public static class Items {
        public static final TagKey<Item> MENACE_FOOD = createTag("menace_food");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MamakiMaker.MOD_ID, name));
        }
    }
}
