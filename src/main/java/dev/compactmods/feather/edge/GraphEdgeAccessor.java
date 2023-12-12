package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

public interface GraphEdgeAccessor {

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> edges(Class<SN> sourceNodeType, Class<TN> targetNodeType);

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> edges(GraphEdgeLookupFunction<SN, TN> func);

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> outboundEdges(SN sourceNode, Class<TN> targetNodeClass);

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> outboundEdges(OutboundGraphEdgeLookupFunction<SN, TN> func, SN sourceNode);
}
