package net.ztoast.mamaki.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.MamakiMaker;
import net.ztoast.mamaki.block.custom.SweetPotatoCropBlock;

public class MamakiBlocks {
    public static final Block LOVE_BLOCK = registerBlock("love_block",
        new Block(AbstractBlock.Settings.create()
            .strength(2.0f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)));
    
    public static final Block SWEET_POTATO_CROP = registerBlockWithoutItem("sweet_potato_crop",
        new SweetPotatoCropBlock(AbstractBlock.Settings.create()
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY)
            .mapColor(MapColor.ORANGE)));
    
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MamakiMaker.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(MamakiMaker.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MamakiMaker.MOD_ID, name),
            new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MamakiMaker.LOGGER.info("Registering Mod Blocks for " + MamakiMaker.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(MamakiBlocks.LOVE_BLOCK);
        });
    }
}
