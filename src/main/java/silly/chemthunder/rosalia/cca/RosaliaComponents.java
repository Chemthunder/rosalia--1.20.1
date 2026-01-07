package silly.chemthunder.rosalia.cca;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.minecraft.entity.player.PlayerEntity;
import silly.chemthunder.rosalia.cca.entity.LongswordPlayerComponent;
import silly.chemthunder.rosalia.cca.item.BlossomingLongswordItemComponent;
import silly.chemthunder.rosalia.cca.item.VesselItemComponent;
import silly.chemthunder.rosalia.item.BlossomingLongswordItem;
import silly.chemthunder.rosalia.item.FloweringVesselItem;

public class RosaliaComponents implements ItemComponentInitializer, EntityComponentInitializer {
    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.register(item -> item instanceof BlossomingLongswordItem, BlossomingLongswordItemComponent.KEY, BlossomingLongswordItemComponent::new);
        registry.register(item -> item instanceof FloweringVesselItem, VesselItemComponent.KEY, VesselItemComponent::new);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, LongswordPlayerComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(LongswordPlayerComponent::new);
    }
}
