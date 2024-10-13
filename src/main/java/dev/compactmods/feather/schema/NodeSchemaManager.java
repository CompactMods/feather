package dev.compactmods.feather.schema;

import dev.compactmods.feather.api.feature.NodeFeature;
import dev.compactmods.feather.api.node.Node;
import dev.compactmods.feather.api.node.NodeSchema;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;

import java.util.Map;
import java.util.Set;

public class NodeSchemaManager<NodeKey> extends SchemaManager<Node<NodeKey>> {
    protected final Map<NodeSchema<NodeKey>, Set<NodeKey>> nodesBySchema;
    protected final Map<NodeFeature<?>, Set<NodeSchema<NodeKey>>> schemasByFeature;

    public NodeSchemaManager() {
        this.nodesBySchema = new Reference2ObjectOpenHashMap<>();
        this.schemasByFeature = new Reference2ObjectOpenHashMap<>();
    }

    public void registerInstance(NodeKey id, NodeSchema<NodeKey> schema) {
        registerSchema(schema);
        var lookup = this.nodesBySchema.computeIfAbsent(schema, s -> new ObjectOpenHashSet<>());
        lookup.add(id);
    }

    public void registerSchema(NodeSchema<NodeKey> schema) {
        this.schemas.add(schema);
        registerSchemaFeatures(schema);
    }

    private void registerSchemaFeatures(NodeSchema<NodeKey> schema) {
        for (var feat : schema.features()) {
            var featureSet = this.schemasByFeature.computeIfAbsent(feat, k -> new ObjectOpenHashSet<>());
            featureSet.add(schema);
        }
    }
}
