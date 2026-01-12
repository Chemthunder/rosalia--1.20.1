package silly.chemthunder.rosalia.index;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.block.EveroseBlockItem;
import silly.chemthunder.rosalia.block.PetrifiedRoseBlockItem;
import silly.chemthunder.rosalia.item.BlossomingLongswordItem;
import silly.chemthunder.rosalia.item.DaisyBellDiscItem;
import silly.chemthunder.rosalia.item.FloweringVesselItem;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    Item BLOSSOMING_LONGSWORD = create("blossoming_longsword", new BlossomingLongswordItem(RosaliaToolMaterials.LONGSWORD, 8, -2.8f, new Item.Settings()
            .maxCount(1)
    ));

    Item FLOWERING_VESSEL = create("flowering_vessel", new FloweringVesselItem(new Item.Settings()
            .maxCount(1)
    ));

    Item DAISY_BELL = create("daisy_bell", new DaisyBellDiscItem(RosaliaSounds.MUSIC_DISC_DAISY_BELL, new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.RARE)
    ));



    Item EVEROSE = create("everose", new EveroseBlockItem(new Item.Settings()
            .maxCount(16)
            .fireproof()
    ));

    Item PETRIFIED_ROSE = create("petrified_rose", new PetrifiedRoseBlockItem(new Item.Settings()
            .maxCount(1)
    ));

    static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, Rosalia.id(name));
        return item;
    }

    static void index() {
        ITEMS.forEach((item, id) -> Registry.register(Registries.ITEM, id, item));
    }
}
