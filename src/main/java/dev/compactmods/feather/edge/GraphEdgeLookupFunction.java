package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphEdgeLookupFunction<OD, Origin extends Node<OD>, DD, Destination extends Node<DD>> {
    Stream<GraphEdge<OD, Origin, DD, Destination>> edges(GraphEdgeAccessor graph);
}
