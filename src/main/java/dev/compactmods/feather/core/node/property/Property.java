package dev.compactmods.feather.core.node.property;

public interface Property<P> {
    Class<P> dataType();
    PropertySchema<P> schema();
}
