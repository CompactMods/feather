package dev.compactmods.feather.core.schema;

public interface SchemaHolder<T extends SchemaHolder<T>> {

    Schema<T> schema();
}
