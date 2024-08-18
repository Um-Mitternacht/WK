package cf.witcheskitchen.common.component;

import cf.witcheskitchen.WitchesKitchen;
import cf.witcheskitchen.common.component.entity.WKCurseComponent;
import cf.witcheskitchen.common.component.entity.WKPlayerComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class WKComponents implements EntityComponentInitializer {
    public static final ComponentKey<WKPlayerComponent> PLAYER_COMPONENT = ComponentRegistry.getOrCreate(WitchesKitchen.id("player"), WKPlayerComponent.class);
    public static final ComponentKey<WKCurseComponent> CURSE_COMPONENT = ComponentRegistry.getOrCreate(WitchesKitchen.id("curse"), WKCurseComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, PLAYER_COMPONENT).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(WKPlayerComponent::new);
        registry.beginRegistration(PlayerEntity.class, CURSE_COMPONENT).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(WKCurseComponent::new);

    }
}
