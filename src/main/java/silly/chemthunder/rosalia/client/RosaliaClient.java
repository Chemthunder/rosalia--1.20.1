package silly.chemthunder.rosalia.client;

import net.fabricmc.api.ClientModInitializer;
import silly.chemthunder.rosalia.index.RosaliaBlocks;
import silly.chemthunder.rosalia.index.RosaliaEntities;
import silly.chemthunder.rosalia.index.RosaliaModelPredicates;
import silly.chemthunder.rosalia.index.RosaliaParticles;

public class RosaliaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RosaliaParticles.clientIndex();
        RosaliaEntities.clientIndex();
        RosaliaBlocks.clientIndex();
        RosaliaModelPredicates.clientIndex();
    }
}
