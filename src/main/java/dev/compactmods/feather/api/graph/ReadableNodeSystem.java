package dev.compactmods.feather.api.graph;

import dev.compactmods.feather.edge.NodeSystemEdgeManager;
import dev.compactmods.feather.node.NodeSystemFeatureManager;

import java.util.stream.Stream;

public interface ReadableNodeSystem<NodeKey> {
    Stream<NodeKey> nodeIDs();

    NodeSystemEdgeManager<NodeKey> edgeManager();
    NodeSystemFeatureManager<NodeKey> featureManager();
}
