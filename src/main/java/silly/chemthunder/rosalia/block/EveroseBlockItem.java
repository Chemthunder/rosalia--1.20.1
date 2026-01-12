package silly.chemthunder.rosalia.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import silly.chemthunder.rosalia.index.RosaliaBlocks;

public class EveroseBlockItem extends BlockItem {
    public EveroseBlockItem(Settings settings) {
        super(RosaliaBlocks.EVEROSE, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(0xffd17a));
    }

}
