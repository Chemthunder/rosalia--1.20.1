package silly.chemthunder.rosalia.client.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.entity.ThornEntity;

public class ThornEntityRenderer extends ProjectileEntityRenderer<ThornEntity> {
    public ThornEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ThornEntity entity) {
        return Rosalia.id("textures/entity/wtf.png");
    }


}
