package dev.compactmods.feather.api.feature;

import dev.compactmods.feather.api.property.PropertyDataStore;

public interface BasicNodeFeatures {
    InstancedNodeFeature<PropertyDataStore> DATA_HOST = () -> PropertyDataStore.class;

    // NodeFeature PROPERTY_LOGIC = new NodeFeature<>() {};
}
