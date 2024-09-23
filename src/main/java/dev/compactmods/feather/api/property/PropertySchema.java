package dev.compactmods.feather.api.property;

import dev.compactmods.feather.api.schema.Schema;

import java.util.function.Supplier;

public interface PropertySchema<P> extends Schema<Property<P>> {
    Class<P> dataType();
    Supplier<P> generator();
    boolean isOptional();
}
