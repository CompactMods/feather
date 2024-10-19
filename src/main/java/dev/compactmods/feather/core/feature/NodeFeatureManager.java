package dev.compactmods.feather.core.feature;

import dev.compactmods.feather.core.node.NodeSchema;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import org.jetbrains.annotations.Nullable;

public class NodeFeatureManager<NodeKey> {

    private final NodeKey nodeID;
    private Reference2ObjectArrayMap<NodeFeature<?>, Object> featureInstances;

    public NodeFeatureManager(NodeKey nodeID, NodeSchema<NodeKey> schema) {
        this.nodeID = nodeID;
        this.featureInstances = new Reference2ObjectArrayMap<>();

        createAndRegisterFeatures(schema);
    }

    public void createAndRegisterFeatures(NodeSchema<NodeKey> schema) {
        for(var feat : schema.features()) {
            var initializer = schema.featureInitializer(feat);
            var instance = initializer.createInstance(nodeID, schema);

            this.featureInstances.put(feat, instance);
        }
    }

    @Nullable
    public <TFeatClass> TFeatClass getFeature(NodeFeature<TFeatClass> feature) {
        final var inst = featureInstances.get(feature);
        if(inst == null) return null;
        if(feature.featureClass().isInstance(inst)) {
            return feature.featureClass().cast(inst);
        }

        return null;
    }
}
