package dev.compactmods.feather.api.node.stream;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.api.node.Node;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphAdjacentNodeStream {

    Stream<Node> nodes(NodeSystem graph, Node originNode);
}
