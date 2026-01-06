package silly.chemthunder.rosalia.index;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.item.BlossomingLongswordItem;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    Item BLOSSOMING_LONGSWORD = create("blossoming_longsword", new BlossomingLongswordItem(RosaliaToolMaterials.LONGSWORD, 8, -2.8f, new Item.Settings()
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
