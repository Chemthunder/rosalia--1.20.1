package silly.chemthunder.rosalia.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import silly.chemthunder.rosalia.index.RosaliaItems;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        ItemEntity entity = (ItemEntity) (Object) this;

        if (state.isOf(Blocks.LAVA)) {
            this.discard();
            dropStack(RosaliaItems.EVEROSE.getDefaultStack(), entity.getStack().getCount());
        }

        super.onBlockCollision(state);
    }
}
