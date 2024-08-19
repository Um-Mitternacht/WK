package cf.witcheskitchen.api.event.network;

import com.mojang.datafixers.util.Function7;
import com.mojang.datafixers.util.Function8;
import com.mojang.datafixers.util.Function9;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface CustomPacketCodecs {
    PacketCodec<PacketByteBuf, Vec3d> VECTOR3D = PacketCodec.of(
        (value, buf) ->
            buf.writeDouble(value.x)
                .writeDouble(value.y)
                .writeDouble(value.z),
        buf -> new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble())
    );

    PacketCodec<PacketByteBuf, SoundCategory> SOUND_CATEGORY = createEnumCodec(SoundCategory::valueOf);

    PacketCodec<RegistryByteBuf, List<Ingredient>> INGREDIENT_LIST = createListCodec(Ingredient.PACKET_CODEC);

    static <T extends Enum<T>> PacketCodec<PacketByteBuf, T> createEnumCodec(Function<String, T> function) {
        return PacketCodec.of(
            (value, buf) -> buf.writeString(value.name()),
            buf -> function.apply(buf.readString())
        );
    }

    static <T, V extends PacketByteBuf> PacketCodec<V, List<T>> createListCodec(PacketCodec<V, T> codec) {
        return PacketCodec.of((list, buf) -> {
            buf.writeVarInt(list.size());
            for (T value : list) {
                codec.encode(buf, value);
            }
        }, buf -> {
            var list = new LinkedList<T>();
            var length = buf.readVarInt();

            for (int i = 0; i < length; i++) {
                list.add(codec.decode(buf));
            }

            return list;
        });
    }

    static <T, V extends PacketByteBuf> PacketCodec<V, Set<T>> createSetCodec(PacketCodec<V, T> codec) {
        return PacketCodec.of((set, buf) -> {
            buf.writeVarInt(set.size());
            for (T value : set) {
                codec.encode(buf, value);
            }
        }, buf -> {
            var set = new HashSet<T>();
            var length = buf.readVarInt();

            for (int i = 0; i < length; i++) {
                set.add(codec.decode(buf));
            }

            return set;
        });
    }

    // yes, we needed more
    static <B, C, T1, T2, T3, T4, T5, T6, T7> PacketCodec<B, C> tuple(
        final PacketCodec<? super B, T1> codec1, final Function<C, T1> from1,
        final PacketCodec<? super B, T2> codec2, final Function<C, T2> from2,
        final PacketCodec<? super B, T3> codec3, final Function<C, T3> from3,
        final PacketCodec<? super B, T4> codec4, final Function<C, T4> from4,
        final PacketCodec<? super B, T5> codec5, final Function<C, T5> from5,
        final PacketCodec<? super B, T6> codec6, final Function<C, T6> from6,
        final PacketCodec<? super B, T7> codec7, final Function<C, T7> from7,
        final Function7<T1, T2, T3, T4, T5, T6, T7, C> to
    ) {
        return new PacketCodec<>() {
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8);
            }

            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8> PacketCodec<B, C> tuple(
        final PacketCodec<? super B, T1> codec1, final Function<C, T1> from1,
        final PacketCodec<? super B, T2> codec2, final Function<C, T2> from2,
        final PacketCodec<? super B, T3> codec3, final Function<C, T3> from3,
        final PacketCodec<? super B, T4> codec4, final Function<C, T4> from4,
        final PacketCodec<? super B, T5> codec5, final Function<C, T5> from5,
        final PacketCodec<? super B, T6> codec6, final Function<C, T6> from6,
        final PacketCodec<? super B, T7> codec7, final Function<C, T7> from7,
        final PacketCodec<? super B, T8> codec8, final Function<C, T8> from8,
        final Function8<T1, T2, T3, T4, T5, T6, T7, T8, C> to
    ) {
        return new PacketCodec<>() {
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9);
            }

            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
            }
        };
    }

    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> PacketCodec<B, C> tuple(
        final PacketCodec<? super B, T1> codec1, final Function<C, T1> from1,
        final PacketCodec<? super B, T2> codec2, final Function<C, T2> from2,
        final PacketCodec<? super B, T3> codec3, final Function<C, T3> from3,
        final PacketCodec<? super B, T4> codec4, final Function<C, T4> from4,
        final PacketCodec<? super B, T5> codec5, final Function<C, T5> from5,
        final PacketCodec<? super B, T6> codec6, final Function<C, T6> from6,
        final PacketCodec<? super B, T7> codec7, final Function<C, T7> from7,
        final PacketCodec<? super B, T8> codec8, final Function<C, T8> from8,
        final PacketCodec<? super B, T9> codec9, final Function<C, T9> from9,
        final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> to
    ) {
        return new PacketCodec<>() {
            public C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10);
            }

            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
            }
        };
    }
}
