package dev.compactmods.feather.tests;

import dev.compactmods.feather.edge.GraphEdgeLookupFunction;
import dev.compactmods.feather.node.GraphNodeStream;
import dev.compactmods.feather.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemGraphTests {
    static final Predicate<StringNode> NODE_1_FILTER = n -> n.data().equals("test1");
    static final GraphNodeStream<StringNode> NODE_1_STREAM = g -> g.nodes(StringNode.class).filter(NODE_1_FILTER);

    static final GraphEdgeLookupFunction<StringNode, StringNode> STRING_TO_STRING_LOOKUP =
            (edgeAccessor) -> edgeAccessor.edges(StringNode.class, StringNode.class);

    @Test
    public void canCreateBasicGraph() {
        final var graph = TestUtils.createBasicGraph(1);

        Assertions.assertNotNull(graph);
    }

    @Test
    public void canFindNodeViaTraversalFunction() throws Exception {
        final var graph = TestUtils.createBasicGraph(1);

        Assertions.assertNotNull(graph);

        final Node<String> node = graph.nodes(NODE_1_STREAM)
                .findFirst()
                .orElseThrow();

        Assertions.assertNotNull(node);
        Assertions.assertInstanceOf(Node.class, node);
        Assertions.assertEquals("test1", node.data());
    }

    @Test
    public void canLookupEdgesByType() throws Exception {
        final var graph = TestUtils.createBasicGraph(10);

        Assertions.assertNotNull(graph);

        final var matching = graph.edges(STRING_TO_STRING_LOOKUP)
                .toList();

        Assertions.assertTrue(matching.size() > 0);

        final var firstResult = matching.get(0);
        Assertions.assertInstanceOf(StringNode.class, firstResult.target().get());

        final var t = firstResult.target().get();
        Assertions.assertNotNull(t);
        Assertions.assertEquals("conn_2", t.data());
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
