package silly.chemthunder.rosalia.index;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.cca.entity.LongswordPlayerComponent;

public interface RosaliaModelPredicates {

    static void clientIndex() {
        ModelPredicateProviderRegistry.register(RosaliaItems.BLOSSOMING_LONGSWORD, new Identifier("blocking"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity instanceof PlayerEntity player) {
                if (LongswordPlayerComponent.KEY.get(player).isDashing) {
                    return 1.0f;
                }
            }
            return 0.0f;
        });
    }
}
