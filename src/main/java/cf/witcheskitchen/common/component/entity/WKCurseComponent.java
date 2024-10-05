package cf.witcheskitchen.common.component.entity;

import cf.witcheskitchen.api.util.CursePair;
import cf.witcheskitchen.common.curse.Curse;
import cf.witcheskitchen.common.registry.WKRegistries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

import java.util.HashSet;
import java.util.Set;

public class WKCurseComponent implements ServerTickingComponent, AutoSyncedComponent {
    private final Set<CursePair> curses = new HashSet<>();
    private final PlayerEntity player;

    public WKCurseComponent(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void serverTick() {
        for (CursePair cursePair : getCurses()) {
            if (cursePair.getDuration() > 0) {
                cursePair.getCurse().tick(player);
                cursePair.setDuration(cursePair.getDuration() - 1);
            } else {
                removeCurse(cursePair.getCurse());
            }
        }
    }

    public void addCurse(Curse curse, int duration) {
        if (hasCurse(curse)) {
            for (CursePair cursePair : getCurses()) {
                if (cursePair.getCurse() == curse) {
                    cursePair.setDuration(duration);
                    curse.onAdded(this.player);
                    return;
                }
            }
        }
        getCurses().add(new CursePair(curse, duration));
        curse.onAdded(this.player);
    }

    public void removeCurse(Curse curse) {
        if (hasCurse(curse)) {
            for (CursePair cursePair : getCurses()) {
                if (cursePair.getCurse() == curse) {
                    cursePair.getCurse().onRemoved(this.player);
                    getCurses().remove(cursePair);
                }
            }
        }
    }

    @Override
    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList cursesList = nbt.getList("Curses", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < cursesList.size(); i++) {
            NbtCompound curseCompound = cursesList.getCompound(i);
            addCurse(WKRegistries.CURSES.get(Identifier.tryParse(curseCompound.getString("Curse"))), curseCompound.getInt("Duration"));
        }
    }

    @Override
    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.put("Curses", toNbtCurse());
    }

    public Set<CursePair> getCurses() {
        return curses;
    }

    public boolean hasCurse(Curse curse) {
        return getCurses().stream().anyMatch(c -> c.getCurse() == curse);
    }

    public NbtList toNbtCurse() {
        NbtList cursesList = new NbtList();
        for (CursePair cursePair : getCurses()) {
            NbtCompound curseCompound = new NbtCompound();
            curseCompound.putString("Curse", WKRegistries.CURSES.getId(cursePair.getCurse()).toString());
            curseCompound.putInt("Duration", cursePair.getDuration());
            cursesList.add(curseCompound);
        }
        return cursesList;
    }
}
