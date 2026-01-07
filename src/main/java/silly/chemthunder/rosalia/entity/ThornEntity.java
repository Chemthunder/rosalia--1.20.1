package silly.chemthunder.rosalia.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import silly.chemthunder.rosalia.index.RosaliaDamageSources;

public class ThornEntity extends PersistentProjectileEntity implements Ownable {
    public ThornEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return Items.AIR.getDefaultStack();
    }



    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        if (this.getOwner() != null) {
            entity.damage(RosaliaDamageSources.thorn(this.getOwner()), 5.0f);
            this.discard();
        }
        super.onEntityHit(entityHitResult);
    }
}
