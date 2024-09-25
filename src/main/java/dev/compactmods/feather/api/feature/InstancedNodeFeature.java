package dev.compactmods.feather.api.feature;

import dev.compactmods.feather.api.property.PropertyDataStore;

import java.util.UUID;

public interface InstancedNodeFeature<T> extends NodeFeature<T> {

    @FunctionalInterface
    interface Initializer<NodeKey, TFeature, TFeatureHost> {
        TFeature createInstance(NodeKey nodeID, TFeatureHost host);
    }
}
