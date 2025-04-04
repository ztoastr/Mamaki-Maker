package net.ztoast.mamaki.entity.client;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.MamakiMaker;
import net.ztoast.mamaki.MamakiMakerClient;
import net.ztoast.mamaki.entity.custom.MenaceEntity;

public class MenaceRenderer extends BipedEntityRenderer<MenaceEntity, MenaceModel<MenaceEntity>>{

    public MenaceRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new MenaceModel<MenaceEntity>(ctx.getPart(MamakiMakerClient.MENACE)), 0.5f);
    }

    @Override
    public Identifier getTexture(MenaceEntity entity) {
        return Identifier.of(MamakiMaker.MOD_ID, "textures/entity/menace/maid.png");
    }

}
