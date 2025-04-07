package net.ztoast.mamaki.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.ztoast.mamaki.block.MamakiBlocks;
import net.ztoast.mamaki.item.MamakiItems;

public class MamakiRecipeProvider extends FabricRecipeProvider {

    public MamakiRecipeProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, List.of(MamakiItems.SWEET_POTATO), RecipeCategory.FOOD, MamakiItems.BAKED_SWEET_POTATO, 0.25f, 200, "baked_sweet_potato");
        
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, MamakiItems.KISS, RecipeCategory.DECORATIONS, MamakiBlocks.LOVE_BLOCK);
    }
}
