package dev.compactmods.feather.api.feature;

import dev.compactmods.feather.api.property.PropertyDataStore;

public interface BasicNodeFeatures {
    NodeFeature<PropertyDataStore> PROPERTY_DATA_STORE = () -> PropertyDataStore.class;

    // NodeFeature PROPERTY_LOGIC = new NodeFeature<>() {};
}
