package silly.chemthunder.rosalia.index;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.block.EveroseBlock;
import silly.chemthunder.rosalia.block.PetrifiedRoseBlock;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RosaliaBlocks {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

    Block EVEROSE = create("everose", new EveroseBlock(StatusEffects.HEALTH_BOOST, 300, AbstractBlock.Settings.copy(Blocks.SUNFLOWER)
            .luminance(state -> 2)
    ));

    Block PETRIFIED_ROSE = create("petrified_rose", new PetrifiedRoseBlock(StatusEffects.DARKNESS, 100, AbstractBlock.Settings.copy(Blocks.SUNFLOWER)
            .luminance(state -> 5)
    ));



    static <T extends Block> T create(String name, T block) {
        BLOCKS.put(block, Rosalia.id(name));
        return block;
    }

    static void index() {
        BLOCKS.forEach((item, id) -> Registry.register(Registries.BLOCK, id, item));
    }

    static void clientIndex() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                EVEROSE,
                PETRIFIED_ROSE
        );
    }
}
