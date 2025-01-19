package dev.compactmods.feather.node.stream;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphAdjacentNodeStream {

    Stream<Node> nodes(NodeSystem graph, Node originNode);
}
