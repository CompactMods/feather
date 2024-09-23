package dev.compactmods.feather;

import dev.compactmods.feather.api.node.ConnectableNode;
import dev.compactmods.feather.api.node.Node;
import dev.compactmods.feather.api.graph.NodeAccess;
import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.api.node.stream.GraphAdjacentNodeStream;
import dev.compactmods.feather.api.property.PropertyDataStore;
import dev.compactmods.feather.api.schema.Schema;
import dev.compactmods.feather.edge.NodeSystemEdgeManager;
import dev.compactmods.feather.property.SimplePropertyDataStore;
import dev.compactmods.feather.traversal.GraphNodeDataStreamFunction;
import dev.compactmods.feather.traversal.GraphNodeTransformationFunction;
import it.unimi.dsi.fastutil.objects.Object2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@SuppressWarnings({"unused"})
public class NodeSystem implements NodeAccess {

    private final Map<UUID, Schema<? extends Node>> nodeSchemas;
    private final Map<UUID, NodeDataAccess<?>> nodeDataAccess;
    private final Map<Schema<?>, Set<UUID>> nodesBySchema;
    private final NodeSystemEdgeManager edgeManager;

    public NodeSystem() {
        nodeSchemas = new Object2ReferenceArrayMap<>();
        nodeDataAccess = new Reference2ObjectOpenHashMap<>();
        nodesBySchema = new Reference2ObjectOpenHashMap<>();
        this.edgeManager = new NodeSystemEdgeManager();
    }

    public Stream<UUID> nodeIDs() {
        return nodeSchemas.keySet().stream();
    }

    @Override
    public <T> NodeDataAccess<T> nodeData(UUID nodeID) {
        return (NodeDataAccess<T>) nodeDataAccess.get(nodeID);
    }

    @Override
    public <TNode, TSchema extends NodeDataSchema<TNode>> Stream<UUID> nodesByDataSchema(TSchema schema) {
        var bySchema = nodesBySchema.get(schema);
        if(bySchema.isEmpty()) return Stream.empty();
        return bySchema.stream();
    }

//    public <S extends NodeDataAccess<S>> Stream<S> nodes(DataHoldingNode<S> schema) {
//        var bySchema = dataNodeSchemas.get(schema);
//        if (bySchema == null) return Stream.empty();
//
//        return bySchema.stream()
//                .map(WeakReference::get)
//                .filter(Objects::nonNull)
//                .filter(node -> schema.nodeType().isInstance(node))
//                .map(node -> schema.nodeType().cast(node));
//    }

    public Stream<Node> adjNodeStream(GraphAdjacentNodeStream streamFunc, Node originNode) {
        return streamFunc.nodes(this, originNode);
    }

    public Stream<PropertyDataStore<Node>> nodeDataStream(GraphNodeDataStreamFunction streamFunc, Node originNode) {
        return streamFunc.apply(this, originNode);
    }

    public <Out> Out transformFunc(GraphNodeTransformationFunction<Out> streamFunc, Node originNode) {
        return streamFunc.apply(this, originNode);
    }

    public void removeNode(UUID nodeId) {
        this.nodeSchemas.remove(nodeId);
        edgeManager.delete(nodeId);
    }

    public <T extends Node> UUID addNode(Schema<T> schema) {
        final UUID id = UUID.randomUUID();

        var nodesBySchema = this.nodesBySchema.computeIfAbsent(schema, k -> new ObjectOpenHashSet<>());
        nodesBySchema.add(id);

        this.nodeSchemas.put(id, schema);

        if (schema instanceof NodeDataSchema<?> dataSchema) {
            final var newStorage = new SimplePropertyDataStore<>(dataSchema);
            this.nodeDataAccess.put(id, newStorage.makeAccess());

            if(ConnectableNode.class.isAssignableFrom(dataSchema.nodeType())) {
                // Node is connectable - create connection manager
                edgeManager.register(id, dataSchema);
            }
        }

        return id;
    }

//    public <S, SN extends DataNode<S>, T, TN extends DataNode<T>, E extends DirectedDataEdge<SN, TN>> E connectNodes(SN u, TN v, E edge) {
//        this.graph.putEdgeValue(u, v, edge);
//        return edge;
//    }
//
//    @Override
//    public <S, SN extends DataNode<S>, T, TN extends DataNode<T>> Stream<DirectedDataEdge<SN, TN>> edges(Class<SN> sourceNodeType, Class<TN> targetNodeType) {
//        return graph.edges().stream()
//                .filter(ep -> sourceNodeType.isInstance(ep.source()))
//                .filter(ep -> targetNodeType.isInstance(ep.target()))
//                .map(ep -> {
//                    final var val = graph.edgeValue(ep);
//
//                    //noinspection unchecked
//                    return (DirectedDataEdge<SN, TN>) val.orElseThrow();
//                });
//    }
//
//    @Override
//    public <S, SN extends DataNode<S>, T, TN extends DataNode<T>> Stream<DirectedDataEdge<SN, TN>> inboundEdges(TN targetNode, Class<SN> sourceNodeClass) {
//        return GraphTraversalHelper.predecessors(this, targetNode, sourceNodeClass)
//                .map(sn -> graph.edgeValue(sn, targetNode).orElse(null))
//                .filter(Objects::nonNull)
//                .map(e -> {
//                    //noinspection unchecked
//                    return (DirectedDataEdge<SN, TN>) e;
//                });
//    }
//
//    @Override
//    public <S, SN extends DataNode<S>, T, TN extends DataNode<T>> Stream<DirectedDataEdge<SN, TN>> outboundEdges(SN sourceNode, Class<TN> targetNodeClass) {
//        return GraphTraversalHelper.successors(this, sourceNode, targetNodeClass)
//                .map(tn -> graph.edgeValue(sourceNode, tn).orElse(null))
//                .filter(Objects::nonNull)
//                .map(e -> {
//                    //noinspection unchecked
//                    return (DirectedDataEdge<SN, TN>) e;
//                });
//    }
}
