package dev.compactmods.feather.node;

import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.api.property.Property;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public record SimpleNodeDataSchema<S>(Class<S> nodeType, Map<String, Property<?>> propertyMap) implements NodeDataSchema<S> {

    @Override
    public Set<Property<?>> properties() {
        return Set.copyOf(propertyMap.values());
    }

    @Override
    public int connectorCount() {
        return propertyMap.size();
    }

    @Override
    public <P> boolean hasProperty(Property<P> property) {
        return propertyMap.containsKey(property.name());
    }

    @Override
    public <P> Optional<Property<P>> getProperty(String name, Class<P> dataType) {
        final var definition = propertyMap.get(name);
        if(definition == null) return Optional.empty();
        if(!definition.schema().dataType().equals(dataType)) return Optional.empty();
        return Optional.of((Property<P>) definition);
    }
}
