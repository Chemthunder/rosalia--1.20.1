package silly.chemthunder.rosalia.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.font.Font;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import silly.chemthunder.rosalia.Rosalia;

public interface RosaliaItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Rosalia.id("main"));
    ItemGroup A_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RosaliaItems.BLOSSOMING_LONGSWORD))
            .displayName(Text.translatable("itemgroup.rosalia"))
            .build();

    static void index() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, A_GROUP);
        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(RosaliaItemGroups::addEntries);

    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(RosaliaItems.BLOSSOMING_LONGSWORD);
        itemGroup.add(RosaliaItems.FLOWERING_VESSEL);
        itemGroup.add(RosaliaItems.DAISY_BELL);
    }
}
