package net.ztoast.mamaki.utill;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.item.MamakiItems;

public class MamakiLootTableModifiers {
    private static final Identifier GRASS_BLOCK_ID = Identifier.of("minecraft", "blocks/short_grass");
    private static final Identifier TALL_GRASS_BLOCK_ID = Identifier.of("minecraft", "blocks/tall_grass");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tablebuilder, source, registry) -> {
            if (GRASS_BLOCK_ID.equals(key.getValue()) || TALL_GRASS_BLOCK_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.02f))
                    .with(ItemEntry.builder(MamakiItems.SWEET_POTATO))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .build());
                tablebuilder.pool(poolBuilder.build());
            }

            if (LootTables.VILLAGE_PLAINS_CHEST.equals(key)
                || LootTables.VILLAGE_SAVANNA_HOUSE_CHEST.equals(key)
                || LootTables.VILLAGE_DESERT_HOUSE_CHEST.equals(key)
                || LootTables.VILLAGE_SNOWY_HOUSE_CHEST.equals(key)
                || LootTables.VILLAGE_TAIGA_HOUSE_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.20f))
                    .with(ItemEntry.builder(MamakiItems.SWEET_POTATO))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))
                    .build());
                tablebuilder.pool(poolBuilder.build());
            }
        });
    }
}
