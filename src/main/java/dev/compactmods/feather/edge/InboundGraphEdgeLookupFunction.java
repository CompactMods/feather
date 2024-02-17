package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

@FunctionalInterface
public interface InboundGraphEdgeLookupFunction<SN extends Node<?>, TN extends Node<?>> {
    Stream<GraphEdge<SN, TN>> getInboundEdges(GraphEdgeAccessor graph, TN originNode);
}

