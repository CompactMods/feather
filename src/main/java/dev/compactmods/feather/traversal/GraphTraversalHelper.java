package dev.compactmods.feather.traversal;

import dev.compactmods.feather.edge.GraphEdge;
import dev.compactmods.feather.graph.NodeAccessor;
import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

@SuppressWarnings("unused")
public class GraphTraversalHelper {

    public static <SD, S extends Node<SD>, TD, T extends Node<TD>, Edge extends GraphEdge<S, T>> Stream<T> nodes(NodeAccessor graph, Class<T> type) {
        return graph.nodes()
                .filter(type::isInstance)
                .map(type::cast);
    }

    public static <SD, S extends Node<SD>, TD, T extends Node<TD>, Edge extends GraphEdge<S, T>> Stream<T> predecessors(NodeAccessor graph, S sourceNode, Class<T> targetNodeClass) {
        return graph.predecessors(sourceNode)
                .filter(targetNodeClass::isInstance)
                .map(targetNodeClass::cast);
    }

    public static <SD, S extends Node<SD>, TD, T extends Node<TD>, Edge extends GraphEdge<S, T>> Stream<T> successors(NodeAccessor graph, S sourceNode, Class<T> targetNodeClass) {
        return graph.successors(sourceNode)
                .filter(targetNodeClass::isInstance)
                .map(targetNodeClass::cast);
    }
}
