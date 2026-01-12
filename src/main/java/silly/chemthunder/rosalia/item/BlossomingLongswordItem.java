package silly.chemthunder.rosalia.item;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import silly.chemthunder.ozone.api.thingies.CustomBipedEntityModelPoseItem;
import silly.chemthunder.ozone.api.thingies.CustomCritEffectItem;
import silly.chemthunder.ozone.api.thingies.CustomDeathSourceItem;
import silly.chemthunder.rosalia.cca.entity.LongswordPlayerComponent;
import silly.chemthunder.rosalia.cca.item.BlossomingLongswordItemComponent;
import silly.chemthunder.rosalia.entity.ThornEntity;
import silly.chemthunder.rosalia.index.*;

import java.util.List;

public class BlossomingLongswordItem extends SwordItem implements CustomBipedEntityModelPoseItem, CustomCritEffectItem, CustomDeathSourceItem {
    public BlossomingLongswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        BlossomingLongswordItemComponent component = BlossomingLongswordItemComponent.KEY.get(stack);
        return component.getCharge() > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        BlossomingLongswordItemComponent component = BlossomingLongswordItemComponent.KEY.get(stack);
        return Math.round((float) component.getCharge() / 7 * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if (EnchantmentHelper.getLevel(RosaliaEnchantments.SUBDIVIDE, stack) > 0) {
            return 0xff6e94;
        }
        if (EnchantmentHelper.getLevel(RosaliaEnchantments.BOUQUET, stack) > 0) {
            return 0x78ffa3;
        }
        return 0xfff27a;
    }

    @Override
    public Text getName(ItemStack stack) {
        if (EnchantmentHelper.getLevel(RosaliaEnchantments.SUBDIVIDE, stack) > 0) {
            return super.getName(stack).copy().styled(style -> style.withColor(0xff6e94));
        }
        if (EnchantmentHelper.getLevel(RosaliaEnchantments.BOUQUET, stack) > 0) {
            return super.getName(stack).copy().styled(style -> style.withColor(0x78ffa3));
        }
        return super.getName(stack).copy().styled(style -> style.withColor(0xfff27a));
    }

    @Override
    public BipedEntityModel.ArmPose getArmPose(ItemStack itemStack, PlayerEntity playerEntity) {
        if (LongswordPlayerComponent.KEY.get(playerEntity).dashTicks > 0) {
            return BipedEntityModel.ArmPose.BOW_AND_ARROW;
        }
        return BipedEntityModel.ArmPose.CROSSBOW_CHARGE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlossomingLongswordItemComponent component = BlossomingLongswordItemComponent.KEY.get(user.getStackInHand(hand));

        boolean hasSubdivide = EnchantmentHelper.getLevel(RosaliaEnchantments.SUBDIVIDE, user.getStackInHand(hand)) > 0;
        boolean hasBouquet = EnchantmentHelper.getLevel(RosaliaEnchantments.BOUQUET, user.getStackInHand(hand)) > 0;

        if (component.getCharge() >= 7 || user.isCreative()) {
            if (hasSubdivide) {
                subdivide(user);
            } else if (hasBouquet) {
                bouquet(user, world);
            } else {
                base(user, world);
            }
        }

        return super.use(world, user, hand);
    }

    private void subdivide(PlayerEntity user) {
        float f = user.getYaw();
        float g = user.getPitch();
        float h = -MathHelper.sin(f * ((float)Math.PI / 180F)) * MathHelper.cos(g * ((float)Math.PI / 180F));
        float k = -MathHelper.sin(g * ((float)Math.PI / 180F));
        float l = MathHelper.cos(f * ((float)Math.PI / 180F)) * MathHelper.cos(g * ((float)Math.PI / 180F));
        float m = MathHelper.sqrt(h * h + k * k + l * l);
        float n = 3.0F;
        h *= n / m;
        k *= n / m;
        l *= n / m;
        user.addVelocity(h, k, l);

        LongswordPlayerComponent.KEY.get(user).dashTicks = 20;
        LongswordPlayerComponent.KEY.get(user).sync();
    }

    private void bouquet(PlayerEntity player, World world) {
        if (world instanceof ServerWorld serverWorld) {

            for (int i = 0; i < 3; i++) {
                float f = 1.0f;
                ThornEntity thorn = new ThornEntity(RosaliaEntities.THORN, serverWorld);
                thorn.setPosition(player.getX(), player.getY() + 1.5f, player.getZ());

                thorn.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, f * 3.0f, 2.5f);

                thorn.setOwner(player);
                serverWorld.spawnEntity(thorn);
            }

            player.swingHand(player.getActiveHand());
        }
    }

    private void base(PlayerEntity player, World world) {
        Box area = new Box(player.getBlockPos()).expand(10, 10, 10);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

        for (LivingEntity entity : entities) {
            if (entity != player) {
                entity.addVelocity(0, 2, 0);

                entity.damage(entity.getDamageSources().magic(), 6.0f);
                entity.velocityModified = true;
            }
        }

        if (world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 10; i++) {
                serverWorld.spawnParticles(RosaliaParticles.BLOOM,
                        player.getX(),
                        player.getY() + 1.0f,
                        player.getZ(),

                        30,
                        0,
                        0,
                        0,
                        i
                );
            }

            serverWorld.spawnParticles(
                    ParticleTypes.FLASH,
                    player.getX(),
                    player.getY() + 1.0f,
                    player.getZ(),
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity player){
            LongswordPlayerComponent component = LongswordPlayerComponent.KEY.get(player);
            BlossomingLongswordItemComponent item = BlossomingLongswordItemComponent.KEY.get(stack);

            if (component.dashTicks > 0){
                for (LivingEntity livingEntity : world.getNonSpectatingEntities(LivingEntity.class, entity.getBoundingBox().expand(1.0F, 1.0F, 1.0F))) {
                    if (livingEntity != entity && !entity.isTeammate(livingEntity) && (!(livingEntity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingEntity).isMarker()) && entity.squaredDistanceTo(livingEntity) < (double)9.0F) {
                        livingEntity.damage(livingEntity.getDamageSources().generic(), 15);
                    }
                }

                if (item.getCharge() != 0) {
                    item.setCharge(item.getCharge() - 1);
                }

                if (player.isOnGround()) {
                    component.dashTicks = 0;
                    component.sync();
                }
            }
        }
    }

    @Override
    public void critEffect(ItemStack itemStack, World world, LivingEntity user, LivingEntity victim) {
        BlossomingLongswordItemComponent component = BlossomingLongswordItemComponent.KEY.get(itemStack);

        if (component.getCharge() < 7) {
            component.setCharge(component.getCharge() + 1);

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(RosaliaParticles.BLOOM,
                        victim.getX(),
                        victim.getY() + 1.0f,
                        victim.getZ(),
                        6,
                        0.03,
                        0.03,
                        0.03,
                        0.2
                );
            }


        }
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return RosaliaDamageSources.enflower(livingEntity);
    }
}
