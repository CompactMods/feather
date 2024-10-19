package dev.compactmods.feather.property;

import dev.compactmods.feather.core.node.property.PropertySchema;

import java.util.function.Supplier;

public record SimplePropertySchema<T>(Class<T> dataType, Supplier<T> generator, boolean isOptional) implements PropertySchema<T> {

    public static <T> SimplePropertySchema<T> required(T initialValue) {
        return new SimplePropertySchema<>((Class<T>) initialValue.getClass(), () -> initialValue, false);
    }

    public static <T> SimplePropertySchema<T> optional(T initialValue) {
        return new SimplePropertySchema<>((Class<T>) initialValue.getClass(), () -> initialValue, true);
    }
}
