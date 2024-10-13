package dev.compactmods.feather.edge;

import dev.compactmods.feather.api.edge.ConnectionPoint;
import dev.compactmods.feather.api.edge.DirectedEdge;
import dev.compactmods.feather.api.node.Node;
import dev.compactmods.feather.api.node.NodePropertySet;
import dev.compactmods.feather.node.DirectedConnections;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;

public class NodeSystemEdgeManager<NodeKey> {

    private final Map<NodeKey, DirectedConnections<NodeKey>> nodeConnections;
    private final Map<NodeKey, DirectedEdge<Node<NodeKey>, ConnectionPoint<Node<NodeKey>>>> edges;

    public NodeSystemEdgeManager() {
        this.nodeConnections = new Object2ObjectOpenHashMap<>();
        this.edges = new Object2ObjectOpenHashMap<>();
    }

    public <T> DirectedConnections<NodeKey> register(NodeKey id, NodePropertySet dataSchema) {
        final var connections = new DirectedConnections<NodeKey>(dataSchema);
        this.nodeConnections.put(id, connections);
        return connections;
    }

    public void delete(NodeKey nodeId) {
        this.nodeConnections.remove(nodeId);
        this.edges.remove(nodeId);
    }
}
