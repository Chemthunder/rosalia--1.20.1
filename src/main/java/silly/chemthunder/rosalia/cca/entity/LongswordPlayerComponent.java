package silly.chemthunder.rosalia.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.index.RosaliaParticles;

public class LongswordPlayerComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<LongswordPlayerComponent> KEY = ComponentRegistry.getOrCreate(Rosalia.id("longsword_player"), LongswordPlayerComponent.class);
    public int dashTicks = 0;
    private final PlayerEntity player;
    public boolean isDashing = false;

    public LongswordPlayerComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    @Override
    public void tick() {
        if (dashTicks > 0) {
            dashTicks--;
            subdivideEffect(player.getWorld(), player);
            if (dashTicks == 0) {
                sync();
                isDashing = false;
            }
        }
    }

    public void subdivideEffect(World world, PlayerEntity player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(RosaliaParticles.BLOOM,
                    x,
                    y + 1.0f,
                    z,
                    6,
                    0.20,
                    0.20,
                    0.20,
                    0.09
            );

            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                    x,
                    y + 1.0f,
                    z,
                    6,
                    0.20,
                    0.20,
                    0.20,
                    0.09
            );
        }
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.dashTicks = nbtCompound.getInt("dashTicks");
        this.isDashing = nbtCompound.getBoolean("isDashing");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("dashTicks", dashTicks);
        nbtCompound.putBoolean("isDashing", isDashing);
    }
}
