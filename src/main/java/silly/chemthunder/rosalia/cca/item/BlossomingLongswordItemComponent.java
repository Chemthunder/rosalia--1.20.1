package silly.chemthunder.rosalia.cca.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.minecraft.item.ItemStack;
import silly.chemthunder.rosalia.Rosalia;

public class BlossomingLongswordItemComponent extends ItemComponent {
    public static final ComponentKey<BlossomingLongswordItemComponent> KEY = ComponentRegistry.getOrCreate(Rosalia.id("longsword"), BlossomingLongswordItemComponent.class);

    private static final String chargeString = "charge";
    public BlossomingLongswordItemComponent(ItemStack stack) {
        super(stack);
    }

    public int getCharge() {
        return this.getInt(chargeString);
    }

    public void setCharge(int charge) {
        this.putInt(chargeString, charge);
    }
}
