package silly.chemthunder.rosalia;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.rosalia.index.*;

public class Rosalia implements ModInitializer {
	public static final String MOD_ID = "rosalia";

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path); }

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        RosaliaItems.index();
        RosaliaParticles.index();
        RosaliaSounds.index();
        RosaliaEnchantments.index();
        RosaliaItemGroups.index();
        RosaliaEntities.index();
        RosaliaBlocks.index();

		LOGGER.info("Rosalia initialized!!!! :333 >w<");
	}
}