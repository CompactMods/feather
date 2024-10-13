package dev.compactmods.feather.api.feature;

public interface NodeFeature<TFeatClass> {
    Class<TFeatClass> featureClass();

    @FunctionalInterface
    interface Initializer<NodeKey, TFeature, TFeatureHost> {
        TFeature createInstance(NodeKey nodeID, TFeatureHost host);
    }
}
