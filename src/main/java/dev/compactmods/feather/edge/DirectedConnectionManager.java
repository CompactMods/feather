package dev.compactmods.feather.edge;

import dev.compactmods.feather.api.edge.DirectedEdge;
import dev.compactmods.feather.api.edge.ConnectionPoint;
import dev.compactmods.feather.api.node.ConnectableNode;
import dev.compactmods.feather.api.node.Node;
import it.unimi.dsi.fastutil.objects.Object2ReferenceArrayMap;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class DirectedConnectionManager<NodeKey, TConnType extends ConnectionPoint<Node<NodeKey>>> {

    private final WeakReference<ConnectableNode> parentNode;
    private final WeakReference<TConnType> connection;
    private final Map<UUID, WeakReference<DirectedEdge<Node<NodeKey>, TConnType>>> outbound;

    public DirectedConnectionManager(ConnectableNode parentNode, TConnType connection) {
        this.parentNode = new WeakReference<>(parentNode);
        this.connection = new WeakReference<>(connection);
        this.outbound = new Object2ReferenceArrayMap<>();
    }

    public void registerConnection(UUID id, DirectedEdge<Node<NodeKey>, TConnType> edge) {
        this.outbound.put(id, new WeakReference<>(edge));
    }

    public void disconnect(UUID id) {
        this.outbound.remove(id);
    }

    public Stream<DirectedEdge<Node<NodeKey>, TConnType>> stream() {
        return outbound.values().stream().map(WeakReference::get);
    }
}
