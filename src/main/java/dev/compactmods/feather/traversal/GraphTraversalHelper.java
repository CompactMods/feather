package dev.compactmods.feather.traversal;

import dev.compactmods.feather.edge.GraphEdge;
import dev.compactmods.feather.graph.NodeAccessor;
import dev.compactmods.feather.node.Node;

import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class GraphTraversalHelper {

    public static <SD, S extends Node<SD>, TD, T extends Node<TD>, Edge extends GraphEdge<SD, S, TD, T>> Stream<T> nodes(NodeAccessor graph, Class<T> type) {
        return graph.nodes()
                .filter(type::isInstance)
                .map(type::cast);
    }

    public static <SD, S extends Node<SD>, TD, T extends Node<TD>, Edge extends GraphEdge<SD, S, TD, T>> Stream<T> predecessors(NodeAccessor graph, S sourceNode, Class<T> targetNodeClass) {
        return graph.predecessors(sourceNode)
                .filter(targetNodeClass::isInstance)
                .map(targetNodeClass::cast);
    }

    public static <SD, S extends Node<SD>, TD, T extends Node<TD>, Edge extends GraphEdge<SD, S, TD, T>> Stream<T> successors(NodeAccessor graph, S sourceNode, Class<T> targetNodeClass) {
        return graph.successors(sourceNode)
                .filter(targetNodeClass::isInstance)
                .map(targetNodeClass::cast);
    }

//    public static <Edge extends GraphEdge, S extends Node, T extends Node, E extends GraphEdge>
//    Stream<GraphEdgeLookupResult<E, S, T>> edges(S sourceNode, Class<E> edgeType, Class<T> targetNodeType) {
//        return sourceNode.connections(edgeType)
//                .map(e -> {
//                    final var ev = graph.edgeValue(e);
//                    return ev.map(ige -> edgeType.isInstance(ige) ? new GraphEdgeLookupResult<E, S, T>(e, edgeType.cast(ige)) : null)
//                            .orElse(null);
//                })
//                .filter(Objects::nonNull);
//    }
}
