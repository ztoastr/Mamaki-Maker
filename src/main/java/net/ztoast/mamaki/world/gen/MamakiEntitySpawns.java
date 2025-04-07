package net.ztoast.mamaki.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.ztoast.mamaki.entity.MamakiEntities;

public class MamakiEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
            BiomeKeys.BEACH,
            BiomeKeys.CHERRY_GROVE,
            BiomeKeys.FLOWER_FOREST,
            BiomeKeys.MEADOW,
            BiomeKeys.SUNFLOWER_PLAINS
        ), SpawnGroup.CREATURE, MamakiEntities.MENACE, 5, 1, 1);

        SpawnRestriction.register(MamakiEntities.MENACE, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}