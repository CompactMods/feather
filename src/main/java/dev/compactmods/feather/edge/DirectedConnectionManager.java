package dev.compactmods.feather.edge;

import dev.compactmods.feather.api.edge.DirectedEdge;
import dev.compactmods.feather.api.edge.NodeConnectionPoint;
import dev.compactmods.feather.api.node.ConnectableNode;
import it.unimi.dsi.fastutil.objects.Object2ReferenceArrayMap;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class DirectedConnectionManager<TConnStart extends NodeConnectionPoint> {

    private final WeakReference<ConnectableNode> parentNode;
    private final WeakReference<TConnStart> connection;
    private final Map<UUID, WeakReference<DirectedEdge<TConnStart, ?>>> outbound;

    public DirectedConnectionManager(ConnectableNode parentNode, TConnStart connection) {
        this.parentNode = new WeakReference<>(parentNode);
        this.connection = new WeakReference<>(connection);
        this.outbound = new Object2ReferenceArrayMap<>();
    }

    public void registerConnection(UUID id, DirectedEdge<TConnStart, ?> edge) {
        this.outbound.put(id, new WeakReference<>(edge));
    }

    public void disconnect(UUID id) {
        this.outbound.remove(id);
    }

    public Stream<DirectedEdge<?, ?>> stream() {
        return outbound.values().stream().map(WeakReference::get);
    }
}
