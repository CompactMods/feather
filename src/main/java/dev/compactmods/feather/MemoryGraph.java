package dev.compactmods.feather;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import dev.compactmods.feather.edge.GraphEdge;
import dev.compactmods.feather.edge.GraphEdgeAccessor;
import dev.compactmods.feather.edge.GraphEdgeLookupFunction;
import dev.compactmods.feather.edge.OutboundGraphEdgeLookupFunction;
import dev.compactmods.feather.edge.impl.EmptyEdge;
import dev.compactmods.feather.graph.NodeAccessor;
import dev.compactmods.feather.node.GraphAdjacentNodeStream;
import dev.compactmods.feather.node.Node;
import dev.compactmods.feather.node.GraphNodeStream;
import dev.compactmods.feather.traversal.GraphNodeStreamFunction;
import dev.compactmods.feather.traversal.GraphNodeTransformationFunction;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@SuppressWarnings({"unused", "UnstableApiUsage"})
public class MemoryGraph implements NodeAccessor, GraphEdgeAccessor {

    private final MutableValueGraph<Node<?>, GraphEdge<?, ?>> graph;
    private final ConcurrentHashMap<UUID, Node<?>> nodes = new ConcurrentHashMap<>();

    public MemoryGraph() {
        graph = ValueGraphBuilder
                .directed()
                .build();
    }

    public Stream<Node<?>> nodes() {
        return nodes.values().parallelStream();
    }

    @Override
    public <T extends Node<?>> Stream<T> nodes(Class<T> c) {
        return graph.nodes().stream()
                .filter(c::isInstance)
                .map(c::cast);
    }

    @Override
    public <T extends Node<?>> Stream<Node<?>> predecessors(T sourceNode) {
        return graph.predecessors(sourceNode).stream();
    }

    @Override
    public <T extends Node<?>> Stream<Node<?>> successors(T sourceNode) {
        return graph.successors(sourceNode).stream();
    }

    public <T extends Node<?>> Stream<T> nodes(GraphNodeStream<T> streamFunc) {
        return streamFunc.apply(this);
    }

    public <SD, DD, ON extends Node<SD>, DN extends Node<DD>> Stream<DN> adjNodeStream(GraphAdjacentNodeStream<ON, DN> streamFunc, ON originNode) {
        return streamFunc.nodes(this, originNode);
    }

    public <ON extends Node<?>, Out> Stream<Out> nodeStream(GraphNodeStreamFunction<ON, Out> streamFunc, ON originNode) {
        return streamFunc.apply(this, originNode);
    }

    public <ON extends Node<?>, Out> Out transformFunc(GraphNodeTransformationFunction<ON, Out> streamFunc, ON originNode) {
        return streamFunc.apply(this, originNode);
    }

    public <T extends Node<?>> void removeNode(T node) {
        this.nodes.remove(node.id());
        this.graph.removeNode(node);
    }

    public <T extends Node<?>> T addNode(T node) {
        this.graph.addNode(node);
        this.nodes.put(node.id(), node);
        return node;
    }

    @SuppressWarnings("UnusedReturnValue")
    public <U, V> GraphEdge<Node<U>, Node<V>> connectNodes(Node<U> u, Node<V> v) {
        final var edge = new EmptyEdge<>(u, v);
        this.graph.putEdgeValue(u, v, edge);
        return edge;
    }

    public <S, SN extends Node<S>, T, TN extends Node<T>, E extends GraphEdge<SN, TN>> E connectNodes(SN u, TN v, E edge) {
        this.graph.putEdgeValue(u, v, edge);
        return edge;
    }

    @Override
    public <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> edges(Class<SN> sourceNodeType, Class<TN> targetNodeType) {
        return graph.edges().stream()
                .filter(ep -> sourceNodeType.isInstance(ep.source()))
                .filter(ep -> targetNodeType.isInstance(ep.target()))
                .map(ep -> {
                    final var val = graph.edgeValue(ep);

                    //noinspection unchecked
                    return (GraphEdge<SN, TN>) val.orElseThrow();
                });
    }

    @Override
    public <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> edges(GraphEdgeLookupFunction<SN, TN> func) {
        return func.edges(this);
    }

    @Override
    public <S, SN extends Node<S>, T, TN extends Node<T>> Stream<GraphEdge<SN, TN>> outboundEdges(OutboundGraphEdgeLookupFunction<SN, TN> func, SN sourceNode) {
        return func.getOutboundEdges(this, sourceNode);
    }
}
