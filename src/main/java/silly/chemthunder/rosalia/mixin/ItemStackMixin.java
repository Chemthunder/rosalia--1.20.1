package silly.chemthunder.rosalia.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import silly.chemthunder.rosalia.index.RosaliaBlocks;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements FabricItemStack {

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void petrify(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState state = context.getWorld().getBlockState(pos);

        if (player != null) {
            if (state.isOf(Blocks.SCULK_CATALYST) && world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
                if (player.getStackInHand(player.getActiveHand()).isOf(Items.POPPY)) {
                    world.setBlockState(pos.up(), RosaliaBlocks.PETRIFIED_ROSE.getDefaultState());

                    Box area = new Box(pos).expand(10, 10, 10);
                    List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

                    for (LivingEntity entity : entities) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600));
                        entity.playSound(SoundEvents.ENTITY_WARDEN_EMERGE, 1, 1);

                        if (world instanceof ServerWorld serverWorld) {
                            serverWorld.spawnParticles(
                                    ParticleTypes.SCULK_SOUL,
                                    pos.getX() + 0.5f,
                                    pos.getY(),
                                    pos.getZ() + 0.5f,
                                    15,
                                    0.03,
                                    0.03,
                                    0.03,
                                    0.2
                            );
                        }
                    }
                }
            }
        }
    }
}
