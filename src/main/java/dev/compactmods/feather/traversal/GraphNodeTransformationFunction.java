package dev.compactmods.feather.traversal;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.core.node.Node;

@FunctionalInterface
public interface GraphNodeTransformationFunction<Out> {
    Out apply(NodeSystem graph, Node input);
}
