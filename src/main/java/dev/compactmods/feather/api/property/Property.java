package dev.compactmods.feather.api.property;

public interface Property<P> {
    String name();
    Class<P> dataType();
    PropertySchema<P> schema();
}
