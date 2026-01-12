package silly.chemthunder.rosalia.item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;

public class DaisyBellDiscItem extends MusicDiscItem {
    public DaisyBellDiscItem(SoundEvent sound, Settings settings) {
        super(4, sound, settings, 111);
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(0xfff27a));
    }
}
