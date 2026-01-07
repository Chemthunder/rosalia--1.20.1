package silly.chemthunder.rosalia.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import silly.chemthunder.rosalia.index.RosaliaItems;

public class BouquetEnchantment extends Enchantment {
    public BouquetEnchantment(EquipmentSlot... slotTypes) {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, slotTypes);
    }

    // Rarity weight, EquipmentSlot... slot) {
    //        super(weight, EnchantmentTarget.WEAPON, slot);

    @Override
    public int getMinPower(int level) {
        return 20;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(RosaliaItems.BLOSSOMING_LONGSWORD) || stack.isOf(Items.BOOK) || stack.isOf(Items.ENCHANTED_BOOK);
    }
}
