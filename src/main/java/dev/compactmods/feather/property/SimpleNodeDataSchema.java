package dev.compactmods.feather.property;

import dev.compactmods.feather.core.node.NodePropertySet;
import dev.compactmods.feather.core.node.property.Property;
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
