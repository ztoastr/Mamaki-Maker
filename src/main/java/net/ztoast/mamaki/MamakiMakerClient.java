package net.ztoast.mamaki;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.entity.MamakiEntities;
import net.ztoast.mamaki.entity.client.MenaceModel;
import net.ztoast.mamaki.entity.client.MenaceRenderer;

public class MamakiMakerClient implements ClientModInitializer{

    public static final EntityModelLayer MENACE = new EntityModelLayer(Identifier.of(MamakiMaker.MOD_ID, "menace"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(MamakiEntities.MENACE, (context) -> {
            return new MenaceRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MENACE, MenaceModel::getTexturedModelData);
    }
    
}
