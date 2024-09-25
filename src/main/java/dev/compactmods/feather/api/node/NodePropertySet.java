package dev.compactmods.feather.api.node;

import dev.compactmods.feather.api.property.Property;

import java.util.Set;

public interface NodePropertySet {

    <P> boolean hasProperty(Property<P> property);

    Set<Property<?>> properties();
}
