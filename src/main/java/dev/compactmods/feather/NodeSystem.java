package dev.compactmods.feather;

import dev.compactmods.feather.api.feature.NodeFeature;
import dev.compactmods.feather.api.feature.NodeFeatureManager;
import dev.compactmods.feather.api.node.Node;
import dev.compactmods.feather.api.graph.NodeAccess;
import dev.compactmods.feather.api.node.NodeSchema;
import dev.compactmods.feather.edge.NodeSystemEdgeManager;
import dev.compactmods.feather.node.NodeSchemaBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SuppressWarnings({"unused"})
public class NodeSystem<NodeKey> implements NodeAccess<NodeKey> {

    private final Supplier<NodeKey> keyGen;
    private final Map<NodeKey, NodeSchema<NodeKey>> nodeSchemas;
    private final Map<NodeSchema<NodeKey>, Set<NodeKey>> nodesBySchema;
    private final NodeSystemEdgeManager<NodeKey> edgeManager;
    private final Map<NodeKey, NodeFeatureManager<NodeKey>> featureManagers;
    private final Map<NodeFeature<?>, Set<NodeSchema<NodeKey>>> schemasByFeature;

    public NodeSystem(Supplier<NodeKey> keyGenerator) {
        this.keyGen = keyGenerator;
        this.nodeSchemas = new Object2ReferenceArrayMap<>();
        this.nodesBySchema = new Reference2ObjectOpenHashMap<>();
        this.edgeManager = new NodeSystemEdgeManager<>();
        this.featureManagers = new Object2ObjectOpenHashMap<>();
        this.schemasByFeature = new Reference2ObjectOpenHashMap<>();
    }

    public Stream<NodeKey> nodeIDs() {
        return nodeSchemas.keySet().stream();
    }

    @Override
    public <T> Stream<NodeKey> nodesWithFeature(NodeFeature<T> feature) {
        var bySchema = schemasByFeature.get(feature);
        if (bySchema == null || bySchema.isEmpty()) return Stream.empty();

        return bySchema.stream()
                .flatMap(schemaWithFeature -> this.nodesBySchema.get(schemaWithFeature).stream());
    }

    @Override
    public NodeFeatureManager<NodeKey> features(NodeKey nodeID) {
        return featureManagers.get(nodeID);
    }

    public void removeNode(NodeKey nodeId) {
        this.nodeSchemas.remove(nodeId);
        edgeManager.delete(nodeId);
    }

    public <T extends Node> NodeKey addNode(NodeSchema<NodeKey> schema) {
        final var id = keyGen.get();

        registerNewNodeSchema(schema, id);

        this.nodeSchemas.put(id, schema);

        var featureManager = new NodeFeatureManager<>(id, schema);
        this.featureManagers.put(id, featureManager);

        return id;
    }

    private void registerNewNodeSchema(NodeSchema<NodeKey> schema, NodeKey id) {
        var nodesBySchema = this.nodesBySchema.computeIfAbsent(schema, k -> new ObjectOpenHashSet<>());
        nodesBySchema.add(id);
        registerSchemaFeatures(schema);
    }

    private void registerSchemaFeatures(NodeSchema<NodeKey> schema) {
        for (var feat : schema.features()) {
            var featureSet = this.schemasByFeature.computeIfAbsent(feat, k -> new ObjectOpenHashSet<>());
            featureSet.add(schema);
        }
    }

    @Nullable
    public NodeFeatureManager<NodeKey> nodeFeatures(NodeKey nodeId) {
        return featureManagers.get(nodeId);
    }

    public NodeSchema<NodeKey> addSchema(NodeSchema<NodeKey> schema) {
        return registerSchema(schema);
    }

    public NodeSchema<NodeKey> addSchema(NodeSchemaBuilder<NodeKey> builder) {
        var built = builder.build();
        return registerSchema(built);
    }

    public NodeSchema<NodeKey> addSchema(Consumer<NodeSchemaBuilder<NodeKey>> builder) {
        var b = new NodeSchemaBuilder<NodeKey>();
        builder.accept(b);
        var built = b.build();
        return registerSchema(built);
    }

    private @NotNull NodeSchema<NodeKey> registerSchema(NodeSchema<NodeKey> built) {
        this.nodesBySchema.computeIfAbsent(built, s -> new ObjectOpenHashSet<>());
        registerSchemaFeatures(built);
        return built;
    }
}
