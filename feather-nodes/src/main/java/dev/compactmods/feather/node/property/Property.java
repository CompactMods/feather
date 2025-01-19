package dev.compactmods.feather.node.property;

public interface Property<P> {
    Class<P> dataType();
    PropertySchema<P> schema();
}
