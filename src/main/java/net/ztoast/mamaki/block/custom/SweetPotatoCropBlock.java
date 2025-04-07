package net.ztoast.mamaki.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PotatoesBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager.Builder;
import net.ztoast.mamaki.item.MamakiItems;

public class SweetPotatoCropBlock extends PotatoesBlock {

    public SweetPotatoCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return MamakiItems.SWEET_POTATO;
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
