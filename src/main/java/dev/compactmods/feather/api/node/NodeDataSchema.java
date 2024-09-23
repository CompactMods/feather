package dev.compactmods.feather.api.node;

import dev.compactmods.feather.api.property.Property;
import dev.compactmods.feather.api.schema.Schema;
import dev.compactmods.feather.api.property.PropertyDataStore;

import java.util.Optional;
import java.util.Set;

public interface NodeDataSchema<S> extends Schema<S> {

    Class<S> nodeType();

    <P> boolean hasProperty(Property<P> property);

    default Optional<Property<Object>> getProperty(String name) {
        return getProperty(name, Object.class);
    }

    <P> Optional<Property<P>> getProperty(String name, Class<P> dataType);

    Set<Property<?>> properties();

    int connectorCount();
}
