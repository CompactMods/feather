package dev.compactmods.feather.api.node;

import dev.compactmods.feather.api.feature.InstancedNodeFeature;
import dev.compactmods.feather.api.feature.NodeFeature;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class NodeSchema<NodeKey> {
    private final List<NodeFeature<?>> features;
    private final Map<InstancedNodeFeature<?>, InstancedNodeFeature.Initializer<NodeKey, ?,?>> featureInitializers;

    public NodeSchema(List<NodeFeature<?>> features, Map<InstancedNodeFeature<?>, InstancedNodeFeature.Initializer<NodeKey, ?, ?>> featureInitializers) {
        this.features = features;
        this.featureInitializers = new Reference2ReferenceArrayMap<>(featureInitializers);
    }

    public List<NodeFeature<?>> features() {
        return features;
    }

    @NotNull
    public <TFeat, TFeatInst> InstancedNodeFeature.Initializer<NodeKey, TFeat, TFeatInst> featureInitializer(InstancedNodeFeature<TFeat> instancedNodeFeature) {
        if(!featureInitializers.containsKey(instancedNodeFeature))
            throw new RuntimeException("Feature " + instancedNodeFeature + " is not registered");

        return (InstancedNodeFeature.Initializer<NodeKey, TFeat, TFeatInst>) featureInitializers.get(instancedNodeFeature);
    }
}
