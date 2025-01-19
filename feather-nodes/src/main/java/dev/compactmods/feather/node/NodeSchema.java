package dev.compactmods.feather.node;

import dev.compactmods.feather.core.feature.NodeFeature;
import dev.compactmods.feather.core.schema.Schema;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class NodeSchema implements Schema<Node> {
    private final List<NodeFeature<?>> features;
    private final Map<NodeFeature<?>, NodeFeature.Initializer<?,?>> featureInitializers;

    public NodeSchema(List<NodeFeature<?>> features, Map<NodeFeature<?>, NodeFeature.Initializer<?, ?>> featureInitializers) {
        this.features = features;
        this.featureInitializers = new Reference2ReferenceArrayMap<>(featureInitializers);
    }

    public List<NodeFeature<?>> features() {
        return features;
    }

    @NotNull
    public <TFeat, TFeatInst> NodeFeature.Initializer<TFeat, TFeatInst> featureInitializer(NodeFeature<TFeat> instancedNodeFeature) {
        if(!featureInitializers.containsKey(instancedNodeFeature))
            throw new RuntimeException("Feature " + instancedNodeFeature + " is not registered");

        return (NodeFeature.Initializer<TFeat, TFeatInst>) featureInitializers.get(instancedNodeFeature);
    }
}
