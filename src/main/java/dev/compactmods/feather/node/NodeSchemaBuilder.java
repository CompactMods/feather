package dev.compactmods.feather.node;

import dev.compactmods.feather.core.feature.NodeFeature;
import dev.compactmods.feather.core.node.NodeSchema;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;

import java.util.List;
import java.util.Map;

public class NodeSchemaBuilder<NodeKey> {

    private final List<NodeFeature<?>> features;
    private final Map<NodeFeature<?>, NodeFeature.Initializer<NodeKey, ?,?>> featureInitializers;

    public NodeSchemaBuilder() {
        this.features = new ReferenceArrayList<>();
        this.featureInitializers = new Reference2ReferenceArrayMap<>();
    }

    public <T, TInstance> NodeSchemaBuilder<NodeKey> registerFeature(NodeFeature<T> feature, NodeFeature.Initializer<NodeKey, T, TInstance> featureInit) {
        if(!features.contains(feature)) {
            this.features.add(feature);
            this.featureInitializers.put(feature, featureInit);
        }

        return this;
    }

    public NodeSchema<NodeKey> build() {
        return new NodeSchema<>(this.features, this.featureInitializers);
    }
}
