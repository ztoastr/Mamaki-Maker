package net.ztoast.mamaki;

import net.fabricmc.api.ModInitializer;
import net.ztoast.mamaki.block.MamakiBlocks;
import net.ztoast.mamaki.entity.MamakiEntities;
import net.ztoast.mamaki.item.MamakiItemGroups;
import net.ztoast.mamaki.item.MamakiItems;

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
	}
}