package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

public interface GraphEdgeAccessor {

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> edges(Class<SN> sourceNodeType, Class<TN> targetNodeType);

    default <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> edges(GraphEdgeLookupFunction<SN, TN> func) {
        return func.edges(this);
    }

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> inboundEdges(TN targetNode, Class<SN> sourceNodeClass);

    default <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> inboundEdges(InboundGraphEdgeLookupFunction<SN, TN> func, TN targetNode) {
        return func.getInboundEdges(this, targetNode);
    }

    default <S, SN extends Node<S>, T, TN extends Node<T>, E extends GraphEdge<SN, TN>> Stream<E> inboundEdges(TN targetNode, Class<SN> sourceNodeClass, Class<E> edgeClass) {
        return inboundEdges(targetNode, sourceNodeClass)
                .filter(edgeClass::isInstance)
                .map(edgeClass::cast);
    }

    <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> outboundEdges(SN sourceNode, Class<TN> targetNodeClass);

    default <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> outboundEdges(OutboundGraphEdgeLookupFunction<SN, TN> func, SN sourceNode) {
        return func.getOutboundEdges(this, sourceNode);
    }

    default <S, SN extends Node<S>, T, TN extends Node<T>, E extends GraphEdge<SN, TN>> Stream<E> outboundEdges(SN sourceNode, Class<TN> targetNodeClass, Class<E> edgeClass) {
        return outboundEdges(sourceNode, targetNodeClass)
                .filter(edgeClass::isInstance)
                .map(edgeClass::cast);
    }
}
