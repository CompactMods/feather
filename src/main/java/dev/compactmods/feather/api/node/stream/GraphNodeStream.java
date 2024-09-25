package dev.compactmods.feather.api.node.stream;

import dev.compactmods.feather.api.graph.NodeAccess;

import java.util.UUID;
import java.util.stream.Stream;

@FunctionalInterface
public interface GraphNodeStream<NodeKey> {

    Stream<NodeKey> apply(NodeAccess<NodeKey> graph);
}
