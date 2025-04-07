package net.ztoast.mamaki.entity.client;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.ztoast.mamaki.entity.custom.MenaceEntity;

public class MenaceModel<T extends MenaceEntity> extends PlayerEntityModel<T> {
    public MenaceModel(ModelPart root) {
        super(root, true);
    }

    public static TexturedModelData getTexturedModelData() {
        return TexturedModelData.of(PlayerEntityModel.getTexturedModelData(new Dilation(0.0f), true), 64, 64);
    }
}