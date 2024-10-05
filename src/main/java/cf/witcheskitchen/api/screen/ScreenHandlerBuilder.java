package cf.witcheskitchen.api.screen;

import cf.witcheskitchen.common.screenhandler.slot.WKOutputSlot;
import cf.witcheskitchen.common.screenhandler.slot.WKSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.apache.commons.lang3.Range;

import java.util.function.Predicate;

/**
 * A ScreenHandler slot builder.
 *
 * <p> Make sure to call {@link #build()} at the end of the method chaining.</p>
 */
public final class ScreenHandlerBuilder {

    private final WKScreenHandler parent;
    private final PlayerInventory playerInventory;
    private final Inventory inventory;
    private int start;

    public ScreenHandlerBuilder(WKScreenHandler parent) {
        this.parent = parent;
        this.playerInventory = parent.getPlayerInventory();
        this.inventory = parent.getInventory();
    }

    public ScreenHandlerBuilder input(final int index, final int posX, final int posY) {
        this.input(index, posX, posY, stack -> true);
        return this;
    }

    public ScreenHandlerBuilder input(final int index, final int posX, final int posY, final Predicate<ItemStack> canInsert) {
        this.parent.addSlot(new WKSlot(this.inventory, index, posX, posY, canInsert));
        return this;
    }

    public ScreenHandlerBuilder output(final int index, final int posX, final int posY, PlayerEntity player) {
        this.parent.addSlot(new WKOutputSlot(this.inventory, index, posX, posY, player));
        return this;
    }


    public ScreenHandlerBuilder playerSetup() {
        return this.playerSetup(8, 84);
    }

    /**
     * Builds the player inventory, including the hot-bar.
     */
    public ScreenHandlerBuilder playerSetup(int posX, int posY) {
        final int i = this.parent.slots.size();
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.parent.addSlot(new Slot(this.playerInventory, k + j * 9 + 9, posX + k * 18, posY + j * 18));
            }
        }
        posY += 58;
        for (int x = 0; x < 9; x++) {
            this.parent.addSlot(new Slot(this.playerInventory, x, posX + x * 18, posY));
        }
        final int j = this.parent.slots.size() - 1;
        this.parent.addPlayerRange(Range.between(i, j));
        this.start = this.parent.slots.size();
        return this;
    }

    /**
     * Builds the container range.
     * Must be called at the end of the building
     */
    public void build() {
        this.parent.addContainerRange(Range.between(this.start, this.parent.slots.size() - 1));
    }
}
