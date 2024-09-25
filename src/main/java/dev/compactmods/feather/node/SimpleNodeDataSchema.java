package dev.compactmods.feather.node;

import dev.compactmods.feather.api.node.NodePropertySet;
import dev.compactmods.feather.api.property.Property;
import it.unimi.dsi.fastutil.objects.ReferenceCollection;

import java.util.Set;

public record SimpleNodeDataSchema(ReferenceCollection<Property<?>> propertiesCollection) implements NodePropertySet {

    @Override
    public Set<Property<?>> properties() {
        return Set.copyOf(propertiesCollection);
    }

    @Override
    public <P> boolean hasProperty(Property<P> property) {
        return propertiesCollection.contains(property);
    }
}
