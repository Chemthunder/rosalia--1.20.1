package silly.chemthunder.rosalia.block;

import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.text.MutableText;

public class EveroseBlock extends FlowerBlock {
    public EveroseBlock(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }
}
