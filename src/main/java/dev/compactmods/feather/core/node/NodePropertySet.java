package dev.compactmods.feather.core.node;

import dev.compactmods.feather.core.node.property.Property;

import java.util.Set;

public interface NodePropertySet {

    <P> boolean hasProperty(Property<P> property);

    Set<Property<?>> properties();
}
