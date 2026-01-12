package silly.chemthunder.rosalia.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import silly.chemthunder.rosalia.index.RosaliaBlocks;

public class PetrifiedRoseBlockItem extends BlockItem {
    public PetrifiedRoseBlockItem(Settings settings) {
        super(RosaliaBlocks.PETRIFIED_ROSE, settings);
    }


    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(0x296b67));
    }
}
