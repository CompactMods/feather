package dev.compactmods.feather.api.property;

public interface Property<P> {
    Class<P> dataType();
    PropertySchema<P> schema();
}
