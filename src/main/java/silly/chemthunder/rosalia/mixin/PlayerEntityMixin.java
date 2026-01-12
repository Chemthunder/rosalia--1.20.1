package silly.chemthunder.rosalia.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import silly.chemthunder.rosalia.cca.item.VesselItemComponent;
import silly.chemthunder.rosalia.index.RosaliaItems;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends net.minecraft.entity.LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void rosalia$vesselCharge(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getOffHandStack().isOf(RosaliaItems.FLOWERING_VESSEL)) {
            ItemStack stack = player.getOffHandStack();

            VesselItemComponent vessel = VesselItemComponent.KEY.get(stack);

            if (vessel.getCharge() < 3) {
                vessel.setCharge(vessel.getCharge() + 1);
            }
        }
    }


}
