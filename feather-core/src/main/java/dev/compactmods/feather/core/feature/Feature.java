package dev.compactmods.feather.core.feature;

public interface Feature<TFeatClass> {
    Class<TFeatClass> featureClass();

    @FunctionalInterface
    interface Initializer<TFeature, TFeatureHost> {
        TFeature createInstance(TFeatureHost host);
    }
}
