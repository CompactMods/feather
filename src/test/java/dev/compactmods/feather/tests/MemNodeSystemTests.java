package dev.compactmods.feather.tests;

import dev.compactmods.feather.api.node.stream.GraphDataNodeStream;
import dev.compactmods.feather.api.property.PropertyDataStore;
import dev.compactmods.feather.tests.example.StringNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;

public class MemNodeSystemTests {

    static final GraphDataNodeStream<StringNode> NODE_BY_NAME_LOOKUP = (g) -> g.nodesByDataSchema(StringNode.SCHEMA)
            .map(nodeID -> {
                var storage = g.nodeData(nodeID).storage();
                Optional<PropertyDataStore<StringNode>> casted = storage.tryCast(StringNode.SCHEMA);
                return casted.orElse(null);
            })
            .filter(Objects::nonNull)
            .filter(nodeData -> nodeData.valueMatches(StringNode.VALUE, "Test Node 1"));

    // [StringNode]--(????)-->[StringNode]
//    static final GraphEdgeLookupFunction<StringNode, StringNode> STRING_TO_STRING_LOOKUP =
//            (edgeAccessor) -> edgeAccessor.edges(StringNode.class, StringNode.class);

    @Test
    public void canCreateBasicGraph() {
        final var graph = TestUtils.createBasicGraph(1);

        Assertions.assertNotNull(graph);
    }

    @Test
    public void canFindNodeViaDataProperty() throws Exception {
        final var graph = TestUtils.createBasicGraph(1);

        Assertions.assertNotNull(graph);

        final var node = NODE_BY_NAME_LOOKUP.apply(graph)
                .findFirst()
                .orElseThrow();

        Assertions.assertNotNull(node);
        Assertions.assertInstanceOf(PropertyDataStore.class, node);
        Assertions.assertEquals("Test Node 1", node.get(StringNode.VALUE).orElseThrow());
    }

    @Test
    public void canLookupEdgesByType() throws Exception {
        final var graph = TestUtils.createBasicGraph(10);

        Assertions.assertNotNull(graph);

//        final var matching = graph.edges(STRING_TO_STRING_LOOKUP)
//                .toList();
//
//        Assertions.assertTrue(!matching.isEmpty());
//
//        final var firstResult = matching.get(0);
//
//        StringNode target = firstResult.target().get();
//        Assertions.assertNotNull(target);
//        Assertions.assertInstanceOf(StringNode.class, target);
//        Assertions.assertEquals("conn_2", target.value());
    }

//    @Test
//    public void canTraverseEdgesByFunction() throws Exception {
//        final var graph = TestUtils.createBasicGraph(1);
//
//        Assertions.assertNotNull(graph);
//
//        final var node = graph.nodes(String.class)
//                .filter(ns -> ns.data().equals("conn_1"))
//                .findFirst()
//                .orElseThrow();
//
//        final GraphEdgeLookupFunction<Node<String>, EmptyEdge, Node<String>> lookupConnectedTo = EdgeLookups.connectedTo(EmptyEdge.class, node);
//
//        final var connectedToTestNode = graph.edgeStream(lookupConnectedTo).toList();
//
//        Assertions.assertEquals(1, connectedToTestNode.size());
//
//        final var firstResult = connectedToTestNode.get(0);
//        Assertions.assertNotNull(firstResult.edgeValue());
//        Assertions.assertInstanceOf(Node.class, firstResult.target());
//        Assertions.assertEquals("conn_2", firstResult.target().data());
//    }
}
