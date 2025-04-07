package net.ztoast.mamaki;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.ztoast.mamaki.datagen.MamakiBlockTagProvider;
import net.ztoast.mamaki.datagen.MamakiItemTagProvider;
import net.ztoast.mamaki.datagen.MamakiLootTableProvider;
import net.ztoast.mamaki.datagen.MamakiModelProvider;
import net.ztoast.mamaki.datagen.MamakiRecipeProvider;

public class MamakiMakerDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		pack.addProvider(MamakiBlockTagProvider::new);
		pack.addProvider(MamakiItemTagProvider::new);
		pack.addProvider(MamakiLootTableProvider::new);
		pack.addProvider(MamakiModelProvider::new);
		pack.addProvider(MamakiRecipeProvider::new);
	}
}
