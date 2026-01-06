package silly.chemthunder.rosalia.mixin.client.model;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import silly.chemthunder.rosalia.Rosalia;
import silly.chemthunder.rosalia.index.RosaliaItems;

/**
 * This class was created by Vowxky.
 * All rights reserved to the developer.
 */

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (stack.isOf(RosaliaItems.BLOSSOMING_LONGSWORD) && (renderMode != ModelTransformationMode.GUI) && renderMode != ModelTransformationMode.GROUND) {
                return ((ItemRendererAccessor) this).renderer$getModels().getModelManager().getModel(new ModelIdentifier(Rosalia.MOD_ID, "longsword_handheld", "inventory"));
            }
        }
        return value;
    }
}