package dev.compactmods.feather.feature;

import dev.compactmods.feather.core.feature.NodeFeature;
import dev.compactmods.feather.core.node.property.PropertyDataStore;

public interface BasicNodeFeatures {
    NodeFeature<PropertyDataStore> PROPERTY_DATA_STORE = () -> PropertyDataStore.class;

    // NodeFeature PROPERTY_LOGIC = new NodeFeature<>() {};
}
