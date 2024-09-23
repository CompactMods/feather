package dev.compactmods.feather.edge;

import dev.compactmods.feather.api.edge.DirectedEdge;
import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.node.NodeDataConnections;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;
import java.util.UUID;

public class NodeSystemEdgeManager {

    private final Map<UUID, NodeDataConnections<?>> nodeConnections;
    private final Map<UUID, DirectedEdge<?, ?>> edges;

    public NodeSystemEdgeManager() {
        this.nodeConnections = new Object2ObjectOpenHashMap<>();
        this.edges = new Object2ObjectOpenHashMap<>();
    }

    public <T> NodeDataConnections<T> register(UUID id, NodeDataSchema<T> dataSchema) {
        final var connections = new NodeDataConnections<>(dataSchema);
        this.nodeConnections.put(id, connections);
        return connections;
    }

    public void delete(UUID nodeId) {
        this.nodeConnections.remove(nodeId);
        this.edges.remove(nodeId);
    }
}
