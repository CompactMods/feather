package dev.compactmods.feather.node;

import dev.compactmods.feather.core.feature.NodeFeature;
import dev.compactmods.feather.core.node.NodeSchema;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;

import java.util.List;
import java.util.Map;

public class NodeSchemaBuilder {

    private final List<NodeFeature<?>> features;
    private final Map<NodeFeature<?>, NodeFeature.Initializer<?,?>> featureInitializers;

    public NodeSchemaBuilder() {
        this.features = new ReferenceArrayList<>();
        this.featureInitializers = new Reference2ReferenceArrayMap<>();
    }

    public <TFeature, TFeatureHost, TFeatureInit extends NodeFeature.Initializer<TFeature, TFeatureHost>> NodeSchemaBuilder registerFeature(NodeFeature<TFeature> feature, TFeatureInit featureInit) {
        if(!features.contains(feature)) {
            this.features.add(feature);
            this.featureInitializers.put(feature, featureInit);
        }

        return this;
    }

    public NodeSchema build() {
        return new NodeSchema(this.features, this.featureInitializers);
    }
}
