package dev.compactmods.feather;

import dev.compactmods.feather.core.feature.NodeFeatureManager;
import dev.compactmods.feather.core.graph.ReadableNodeSystem;
import dev.compactmods.feather.core.node.NodeSchema;
import dev.compactmods.feather.edge.NodeSystemEdgeManager;
import dev.compactmods.feather.node.NodeSchemaBuilder;
import dev.compactmods.feather.node.NodeSystemFeatureManager;
import dev.compactmods.feather.schema.NodeSchemaManager;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SuppressWarnings({"unused"})
public class NodeSystem<NodeKey> implements ReadableNodeSystem<NodeKey> {

    private final Supplier<NodeKey> keyGen;
    private final Set<NodeKey> registeredNodeIds;
    private final NodeSchemaManager<NodeKey> nodeSchemas;
    private final NodeSystemEdgeManager<NodeKey> edgeManager;
    private final NodeSystemFeatureManager<NodeKey> featureManager;


    public NodeSystem(Supplier<NodeKey> keyGenerator) {
        this.keyGen = keyGenerator;
        this.registeredNodeIds = new ObjectOpenHashSet<>();
        this.nodeSchemas = new NodeSchemaManager<>();
        this.edgeManager = new NodeSystemEdgeManager<>();
        this.featureManager = new NodeSystemFeatureManager<>();
    }

    public Stream<NodeKey> nodeIDs() {
        return registeredNodeIds.stream();
    }

    @Override
    public NodeSystemEdgeManager<NodeKey> edgeManager() {
        return edgeManager;
    }

    @Override
    public NodeSystemFeatureManager<NodeKey> featureManager() {
        return featureManager;
    }

    public NodeFeatureManager<NodeKey> featureManager(NodeKey nodeID) {
        return featureManager.nodeFeatures(nodeID);
    }

    public void removeNode(NodeKey nodeId) {
        this.registeredNodeIds.remove(nodeId);
        edgeManager.delete(nodeId);
    }

    public NodeKey addNode(NodeSchema<NodeKey> schema) {
        final var id = keyGen.get();

        this.registeredNodeIds.add(id);
        this.nodeSchemas.registerInstance(id, schema);
        this.featureManager.register(id, schema);
        return id;
    }

    public NodeSchema<NodeKey> addSchema(Consumer<NodeSchemaBuilder<NodeKey>> builder) {
        var b = new NodeSchemaBuilder<NodeKey>();
        builder.accept(b);

        final var built = b.build();
        nodeSchemas.registerSchema(built);
        return built;
    }
}
