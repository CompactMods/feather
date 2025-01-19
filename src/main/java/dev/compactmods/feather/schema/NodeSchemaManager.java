package dev.compactmods.feather.schema;

import dev.compactmods.feather.core.feature.NodeFeature;
import dev.compactmods.feather.core.node.Node;
import dev.compactmods.feather.core.node.NodeSchema;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;

import java.util.Map;
import java.util.Set;

public class NodeSchemaManager extends SchemaManager<Node> {
    protected final Map<NodeSchema, Set<Node>> nodesBySchema;
    protected final Map<NodeFeature<?>, Set<NodeSchema>> schemasByFeature;

    public NodeSchemaManager() {
        this.nodesBySchema = new Reference2ObjectOpenHashMap<>();
        this.schemasByFeature = new Reference2ObjectOpenHashMap<>();
    }

    public void registerInstance(NodeSchema schema, Node node) {
        registerSchema(schema);
        var lookup = this.nodesBySchema.computeIfAbsent(schema, s -> new ObjectOpenHashSet<>());
        lookup.add(node);
    }

    public void registerSchema(NodeSchema schema) {
        this.schemas.add(schema);
        registerSchemaFeatures(schema);
    }

    private void registerSchemaFeatures(NodeSchema schema) {
        for (var feat : schema.features()) {
            var featureSet = this.schemasByFeature.computeIfAbsent(feat, k -> new ObjectOpenHashSet<>());
            featureSet.add(schema);
        }
    }
}
