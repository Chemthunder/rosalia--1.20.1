package silly.chemthunder.rosalia.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import silly.chemthunder.rosalia.cca.item.BlossomingLongswordItemComponent;
import silly.chemthunder.rosalia.cca.item.VesselItemComponent;
import silly.chemthunder.rosalia.index.RosaliaEnchantments;

public class FloweringVesselItem extends Item {
    public FloweringVesselItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xfff27a;
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(0xfff27a));
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) VesselItemComponent.KEY.get(stack).getCharge() / 3 * 13);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof LivingEntity living) {
            if (!living.getOffHandStack().isOf(this)) {
                VesselItemComponent vesselItemComponent = VesselItemComponent.KEY.get(stack);

                if (vesselItemComponent.getCharge() != 0) {
                    vesselItemComponent.setCharge(vesselItemComponent.getCharge() - 1);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


        return super.use(world, user, hand);
    }
}
