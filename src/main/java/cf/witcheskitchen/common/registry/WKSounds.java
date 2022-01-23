package cf.witcheskitchen.common.registry;

import cf.witcheskitchen.WK;
import cf.witcheskitchen.WKConfig;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WKSounds {

    // Cu-Sith 
    public static SoundEvent CUSITH_IDLE_EVENT = register("witcheskitchen:cusith_ambient");
    public static SoundEvent CUSITH_DEATH_EVENT = register("witcheskitchen:cusith_death");
    public static SoundEvent CUSITH_HOWL_EVENT = register("witcheskitchen:cusith_howl");


    private static SoundEvent register(String id) {
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(new Identifier(id)));
    }


    public static void register() {
        if (WKConfig.get().debugMode) {
            WK.logger.info("Witches Kitchen Base Sounds: Successfully Loaded");
        }
    }

}
