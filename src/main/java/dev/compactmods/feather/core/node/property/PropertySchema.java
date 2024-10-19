package dev.compactmods.feather.core.node.property;

import dev.compactmods.feather.core.schema.Schema;

import java.util.function.Supplier;

public interface PropertySchema<P> extends Schema<Property<P>> {
    Class<P> dataType();
    Supplier<P> generator();
    boolean isOptional();
}
