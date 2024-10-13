package dev.compactmods.feather.node;

import dev.compactmods.feather.api.feature.NodeFeature;
import dev.compactmods.feather.api.feature.NodeFeatureInstance;
import dev.compactmods.feather.api.feature.NodeFeatureManager;
import dev.compactmods.feather.api.node.NodeSchema;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class NodeSystemFeatureManager<NodeKey> implements dev.compactmods.feather.api.feature.NodeSystemFeatureManager<NodeKey> {

    protected final Map<NodeKey, NodeFeatureManager<NodeKey>> featureManagers;
    protected final Map<NodeFeature<?>, Set<NodeKey>> featureNodeLookup;

    public NodeSystemFeatureManager() {
        this.featureManagers = new Object2ObjectOpenHashMap<>();
        this.featureNodeLookup = new Reference2ObjectOpenHashMap<>();
    }

    public <T> Stream<NodeFeatureInstance<NodeKey, T>> nodesWithFeature(NodeFeature<T> feature) {
        var bySchema = featureNodeLookup.get(feature);
        if (bySchema == null || bySchema.isEmpty()) return Stream.empty();

        Stream.Builder<NodeFeatureInstance<NodeKey, T>> stream = Stream.builder();
        bySchema.forEach(nodeID -> {
            var man = featureManagers.get(nodeID);
            if (man == null) return;
            var pair = new NodeFeatureInstance<>(nodeID, man.getFeature(feature));
            stream.add(pair);
        });

        return stream.build();
    }

    @Nullable
    public NodeFeatureManager<NodeKey> nodeFeatures(NodeKey nodeId) {
        return featureManagers.get(nodeId);
    }

    public void register(NodeKey id, NodeSchema<NodeKey> schema) {
        var featureManager = new NodeFeatureManager<>(id, schema);
        this.featureManagers.put(id, featureManager);

        for(final var feat : schema.features()) {
            final var lookupForFeat = featureNodeLookup.computeIfAbsent(feat, k -> new HashSet<>());
            lookupForFeat.add(id);
        }
    }
}
