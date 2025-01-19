package dev.compactmods.feather.property;

import dev.compactmods.feather.node.property.Property;
import dev.compactmods.feather.node.property.PropertySchema;

public record NamedProperty<T>(String name, PropertySchema<T> schema) implements Property<T> {
    @Override
    public Class<T> dataType() {
        return schema.dataType();
    }
}
