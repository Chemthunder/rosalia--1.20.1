package silly.chemthunder.rosalia.index;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.client.entity.render.ThornEntityRenderer;
import silly.chemthunder.rosalia.entity.ThornEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaEntities {
    Map<EntityType<? extends Entity>, Identifier> ENTITIES = new LinkedHashMap<>();

    //  Item WEAPON_RACK = create("weapon_rack", new WeaponRackItem(new Item.Settings()));

    EntityType<ThornEntity> THORN = createEntity("thorn", FabricEntityTypeBuilder.create(SpawnGroup.MISC, ThornEntity::new).disableSaving().dimensions(EntityDimensions.changing(1.0f, 1.0f)).build());

    private static <T extends EntityType<? extends Entity>> T createEntity(String name, T entity) {
        ENTITIES.put(entity, new Identifier(Rosalia.MOD_ID, name));
        return entity;
    }

    static void index() {
        ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
    }

    static void clientIndex() {
        EntityRendererRegistry.register(THORN, ThornEntityRenderer::new);
    }
}
