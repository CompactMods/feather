package dev.compactmods.feather.node;

import dev.compactmods.feather.node.property.Property;

import java.util.Set;

public interface NodePropertySet {

    <P> boolean hasProperty(Property<P> property);

    Set<Property<?>> properties();
}
