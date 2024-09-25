package dev.compactmods.feather.edge;

import dev.compactmods.feather.api.edge.DirectedEdge;
import dev.compactmods.feather.api.node.NodePropertySet;
import dev.compactmods.feather.node.NodeEdgeConnections;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;

public class NodeSystemEdgeManager<NodeKey> {

    private final Map<NodeKey, NodeEdgeConnections<NodeKey>> nodeConnections;
    private final Map<NodeKey, DirectedEdge<?, ?>> edges;

    public NodeSystemEdgeManager() {
        this.nodeConnections = new Object2ObjectOpenHashMap<>();
        this.edges = new Object2ObjectOpenHashMap<>();
    }

    public <T> NodeEdgeConnections<NodeKey> register(NodeKey id, NodePropertySet dataSchema) {
        final var connections = new NodeEdgeConnections<NodeKey>(dataSchema);
        this.nodeConnections.put(id, connections);
        return connections;
    }

    public void delete(NodeKey nodeId) {
        this.nodeConnections.remove(nodeId);
        this.edges.remove(nodeId);
    }
}
