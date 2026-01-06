package silly.chemthunder.rosalia.index;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaParticles {
    Map<ParticleType<?>, Identifier> PARTICLES = new LinkedHashMap<>();

    DefaultParticleType BLOOM = create("bloom", FabricParticleTypes.simple(true));

    static void index() {
        PARTICLES.keySet().forEach(particle -> Registry.register(Registries.PARTICLE_TYPE, PARTICLES.get(particle), particle));
    }

    private static <T extends ParticleType<?>> T create(String name, T particle) {
        PARTICLES.put(particle, Rosalia.id(name));
        return particle;
    }

    static void clientIndex() {
        ParticleFactoryRegistry.getInstance().register(BLOOM, EndRodParticle.Factory::new);
    }
}
