package net.ztoast.mamaki;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.ztoast.mamaki.block.MamakiBlocks;
import net.ztoast.mamaki.entity.MamakiEntities;
import net.ztoast.mamaki.item.MamakiItemGroups;
import net.ztoast.mamaki.item.MamakiItems;
import net.ztoast.mamaki.utill.MamakiLootTableModifiers;
import net.ztoast.mamaki.world.gen.MamakiEntitySpawns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MamakiMaker implements ModInitializer {
	public static final String MOD_ID = "mamaki";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MamakiItemGroups.registerItemGroups();
		MamakiItems.registerModItems();
		MamakiBlocks.registerModBlocks();
		MamakiEntities.registerModEntities();
		MamakiLootTableModifiers.modifyLootTables();
        MamakiEntitySpawns.addSpawns();

		CompostingChanceRegistry.INSTANCE.add(MamakiItems.SWEET_POTATO, 0.5f);
	}
}