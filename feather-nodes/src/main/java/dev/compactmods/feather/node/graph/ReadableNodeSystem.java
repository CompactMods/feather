package dev.compactmods.feather.core.graph;

import dev.compactmods.feather.core.node.Node;
import dev.compactmods.feather.feature.connections.NodeSystemEdgeManager;

import java.util.stream.Stream;

public interface ReadableNodeSystem<NodeKey> {
    Stream<Node> nodes();

    Stream<NodeKey> nodeIDs();

    NodeSystemEdgeManager<NodeKey> edgeManager();
}
