package cf.witcheskitchen.api.util;

import com.mojang.serialization.Codec;

import java.util.function.Function;

public class CodecUtils {
    public static <T extends Enum<T>> Codec<T> createEnumCodec(Function<String, T> function) {
        return Codec.stringResolver(Enum::name, function);
    }
}
