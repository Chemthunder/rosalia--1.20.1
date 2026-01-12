package silly.chemthunder.rosalia.block;

import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;

public class PetrifiedRoseBlock extends FlowerBlock {
    public PetrifiedRoseBlock(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }
}
