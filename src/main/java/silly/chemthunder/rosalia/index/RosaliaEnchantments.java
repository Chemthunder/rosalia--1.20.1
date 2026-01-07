package silly.chemthunder.rosalia.index;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.enchantment.BouquetEnchantment;
import silly.chemthunder.rosalia.enchantment.SubdivideEnchantment;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaEnchantments {
    Map<Enchantment, Identifier> ENCHANTMENTS = new LinkedHashMap<>();

    Enchantment SUBDIVIDE = createEnchantment("subdivide", new SubdivideEnchantment(EquipmentSlot.MAINHAND));
    Enchantment BOUQUET = createEnchantment("bouquet", new BouquetEnchantment(EquipmentSlot.MAINHAND));

    private static Enchantment createEnchantment(String name, Enchantment enchantment) {
        ENCHANTMENTS.put(enchantment, new Identifier(Rosalia.MOD_ID, name));
        return enchantment;
    }

    static void index() {
        ENCHANTMENTS.keySet().forEach(enchantment -> Registry.register(Registries.ENCHANTMENT, ENCHANTMENTS.get(enchantment), enchantment));
    }
}
