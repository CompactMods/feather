package dev.compactmods.feather.api.node;

import dev.compactmods.feather.api.feature.NodeFeature;
import dev.compactmods.feather.api.schema.Schema;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class NodeSchema<NodeKey> implements Schema<Node<NodeKey>> {
    private final List<NodeFeature<?>> features;
    private final Map<NodeFeature<?>, NodeFeature.Initializer<NodeKey, ?,?>> featureInitializers;

    public NodeSchema(List<NodeFeature<?>> features, Map<NodeFeature<?>, NodeFeature.Initializer<NodeKey, ?, ?>> featureInitializers) {
        this.features = features;
        this.featureInitializers = new Reference2ReferenceArrayMap<>(featureInitializers);
    }

    public List<NodeFeature<?>> features() {
        return features;
    }

    @NotNull
    public <TFeat, TFeatInst> NodeFeature.Initializer<NodeKey, TFeat, TFeatInst> featureInitializer(NodeFeature<TFeat> instancedNodeFeature) {
        if(!featureInitializers.containsKey(instancedNodeFeature))
            throw new RuntimeException("Feature " + instancedNodeFeature + " is not registered");

        return (NodeFeature.Initializer<NodeKey, TFeat, TFeatInst>) featureInitializers.get(instancedNodeFeature);
    }
}
