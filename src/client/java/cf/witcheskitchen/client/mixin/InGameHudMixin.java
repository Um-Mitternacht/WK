package cf.witcheskitchen.client.mixin;

import cf.witcheskitchen.api.util.SeedTypeHelper;
import cf.witcheskitchen.common.item.VariantSeedItem;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    private ItemStack currentStack;

    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;getTextRenderer()Lnet/minecraft/client/font/TextRenderer;", ordinal = 1))
    private void wk$renderTypeColor(DrawContext context, CallbackInfo ci, @Local(ordinal = 1) int x, @Local(ordinal = 2) int y, @Local(ordinal = 3) int alpha) {
        if (this.currentStack.getItem() instanceof VariantSeedItem) {
            MutableText text = SeedTypeHelper.getSeedTypeText(this.currentStack);
            if (text != null) {
                int io = this.getTextRenderer().getWidth(text);
                int xo = (this.client.getWindow().getScaledWidth() - io) / 2;
                context.drawTextWithShadow(this.getTextRenderer(), text, xo, y + 9, 16777215 + (alpha << 24));
            }
        }
    }
}
