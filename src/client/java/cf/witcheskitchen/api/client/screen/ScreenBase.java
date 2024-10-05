package cf.witcheskitchen.api.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public abstract class ScreenBase<T extends ScreenHandler> extends HandledScreen<T> {

    private final ScreenBuilder builder;

    public ScreenBase(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.builder = new ScreenBuilder(this);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.builder.drawContainer(context, this.x, this.y, this.backgroundWidth, this.backgroundHeight);
        this.builder.drawPlayerSlots(context, this.x, this.y);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        super.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    // Centers title
    @Override
    protected void init() {
        super.init();
        this.titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        this.titleY = 14;
    }

    // Public access
    @Override
    public boolean isPointWithinBounds(int x, int y, int width, int height, double pointX, double pointY) {
        return super.isPointWithinBounds(x, y, width, height, pointX, pointY);
    }

    // Getters for positions within our background
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // Background dimensions
    public int getWidth() {
        return this.backgroundWidth;
    }

    public int getHeight() {
        return this.backgroundHeight;
    }

    public ScreenBuilder builder() {
        return builder;
    }
}
