package dev.compactmods.feather;

import dev.compactmods.feather.core.feature.NodeFeatureManager;
import dev.compactmods.feather.core.graph.ReadableNodeSystem;
import dev.compactmods.feather.core.node.Node;
import dev.compactmods.feather.core.node.NodeSchema;
import dev.compactmods.feather.feature.connections.NodeSystemEdgeManager;
import dev.compactmods.feather.node.NodeSchemaBuilder;
import dev.compactmods.feather.schema.NodeSchemaManager;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SuppressWarnings({"unused"})
public class NodeSystem<NodeKey> implements ReadableNodeSystem<NodeKey> {

    private final Supplier<NodeKey> keyGen;
    private final Map<NodeKey, Node> nodes;
    private final NodeSchemaManager nodeSchemas;
    private final NodeSystemEdgeManager<NodeKey> edgeManager;

    public NodeSystem(Supplier<NodeKey> keyGenerator) {
        this.keyGen = keyGenerator;
        this.nodes = new Object2ObjectOpenHashMap<>();
        this.nodeSchemas = new NodeSchemaManager();
        this.edgeManager = new NodeSystemEdgeManager<>();
    }

    @Override
    public Stream<Node> nodes() {
        return nodes.values().stream();
    }

    public Stream<NodeKey> nodeIDs() {
        return nodes.keySet().stream();
    }

    @Override
    public NodeSystemEdgeManager<NodeKey> edgeManager() {
        return edgeManager;
    }

    public NodeFeatureManager featureManager(NodeKey nodeID) {
        final var node = node(nodeID);
        if(node == null) return null;
        return node.features();
    }

    @Nullable
    public Node node(NodeKey key) {
        return nodes.get(key);
    }

    public void removeNode(NodeKey nodeId) {
        this.nodes.remove(nodeId);
        edgeManager.delete(nodeId);
    }

    public NodeKey addNode(Node instance) {
        final var id = keyGen.get();
        this.nodes.put(id, instance);
        this.nodeSchemas.registerInstance(instance.schema(), instance);
        return id;
    }

    public NodeSchema addSchema(Consumer<NodeSchemaBuilder> builder) {
        var b = new NodeSchemaBuilder();
        builder.accept(b);

        final var built = b.build();
        nodeSchemas.registerSchema(built);
        return built;
    }
}
