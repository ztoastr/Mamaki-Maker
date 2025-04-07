package net.ztoast.mamaki.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.ztoast.mamaki.item.MamakiItems;
import net.ztoast.mamaki.utill.MamakiTags;

public class MamakiItemTagProvider extends ItemTagProvider {

    public MamakiItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(MamakiTags.Items.MENACE_FOOD)
            .add(MamakiItems.SWEET_POTATO)
            .add(MamakiItems.BAKED_SWEET_POTATO);
    }
    
}
