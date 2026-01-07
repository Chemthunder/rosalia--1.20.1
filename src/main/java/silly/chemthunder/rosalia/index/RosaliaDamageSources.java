package silly.chemthunder.rosalia.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.rosalia.Rosalia;

public interface RosaliaDamageSources {
    RegistryKey<DamageType> ENFLOWER = of("enflower");

    static DamageSource enflower(LivingEntity entity) {
        return entity.getDamageSources().create(ENFLOWER); }

    RegistryKey<DamageType> THORN = of("thorn");

    static DamageSource thorn(Entity entity) {
        return entity.getDamageSources().create(THORN); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Rosalia.id(name));
    }
}
