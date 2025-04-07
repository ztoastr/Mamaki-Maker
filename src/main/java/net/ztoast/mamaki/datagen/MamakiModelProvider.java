package net.ztoast.mamaki.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.ztoast.mamaki.block.MamakiBlocks;
import net.ztoast.mamaki.block.custom.SweetPotatoCropBlock;
import net.ztoast.mamaki.item.MamakiItems;

public class MamakiModelProvider extends FabricModelProvider {

    public MamakiModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(MamakiBlocks.LOVE_BLOCK);

        blockStateModelGenerator.registerCrop(MamakiBlocks.SWEET_POTATO_CROP, SweetPotatoCropBlock.AGE, 0, 0, 1, 1, 1, 2, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(MamakiItems.KISS, Models.GENERATED);
        
        //itemModelGenerator.register(MamakiItems.SWEET_POTATO, Models.GENERATED);
        itemModelGenerator.register(MamakiItems.BAKED_SWEET_POTATO, Models.GENERATED);
    }
    
}
