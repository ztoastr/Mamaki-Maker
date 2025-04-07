package net.ztoast.mamaki.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ztoast.mamaki.MamakiMaker;
import net.ztoast.mamaki.entity.custom.MenaceEntity;

public class MamakiEntities {
    public static final EntityType<MenaceEntity> MENACE = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(MamakiMaker.MOD_ID, "menace"),
        EntityType.Builder.create(MenaceEntity::new, SpawnGroup.CREATURE).dimensions(1, 2f).build());

    public static void registerModEntities(){
        MamakiMaker.LOGGER.info("Registering Mod Entities for " + MamakiMaker.MOD_ID);
        FabricDefaultAttributeRegistry.register(MENACE, MenaceEntity.createAttributes());
    }
    
}
