package dev.compactmods.feather.api.node;

import dev.compactmods.feather.api.feature.NodeFeature;
import dev.compactmods.feather.api.property.PropertyDataStore;

public interface NodeDataAccess extends NodeFeature {
    NodePropertySet schema();

    PropertyDataStore storage();
}