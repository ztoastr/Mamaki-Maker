package net.ztoast.mamaki.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.ztoast.mamaki.block.MamakiBlocks;
import net.ztoast.mamaki.block.custom.SweetPotatoCropBlock;
import net.ztoast.mamaki.item.MamakiItems;

public class MamakiLootTableProvider extends FabricBlockLootTableProvider {

    public MamakiLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(MamakiBlocks.LOVE_BLOCK);

        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(MamakiBlocks.SWEET_POTATO_CROP)
            .properties(StatePredicate.Builder.create()
            .exactMatch(SweetPotatoCropBlock.AGE, SweetPotatoCropBlock.MAX_AGE));
        this.addDrop(MamakiBlocks.SWEET_POTATO_CROP, this.cropDrops(
            MamakiBlocks.SWEET_POTATO_CROP, 
            MamakiItems.SWEET_POTATO, 
            MamakiItems.SWEET_POTATO, 
            builder
        ));
    }
    
}
