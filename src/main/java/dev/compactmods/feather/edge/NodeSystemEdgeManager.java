package dev.compactmods.feather.edge;

import dev.compactmods.feather.core.edge.ConnectionPoint;
import dev.compactmods.feather.core.edge.DirectedEdge;
import dev.compactmods.feather.core.node.Node;
import dev.compactmods.feather.core.node.NodePropertySet;
import dev.compactmods.feather.node.NodeConnections;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class NodeSystemEdgeManager<NodeKey> {

    private final Map<NodeKey, NodeConnections<NodeKey>> nodeConnections;
    private final Map<NodeKey, DirectedEdge<Node<NodeKey>, ConnectionPoint<Node<NodeKey>>>> edges;

    public NodeSystemEdgeManager() {
        this.nodeConnections = new Object2ObjectOpenHashMap<>();
        this.edges = new Object2ObjectOpenHashMap<>();
    }

    public NodeConnections<NodeKey> register(NodeKey id, NodePropertySet dataSchema) {
        final var connections = new NodeConnections<>(id, dataSchema);
        this.nodeConnections.put(id, connections);
        return connections;
    }

    @Nullable
    public NodeConnections<NodeKey> get(NodeKey id) {
        return this.nodeConnections.get(id);
    }

    public void delete(NodeKey nodeId) {
        this.nodeConnections.remove(nodeId);
        this.edges.remove(nodeId);
    }
}
