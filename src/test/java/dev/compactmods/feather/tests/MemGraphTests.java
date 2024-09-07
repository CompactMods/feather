package dev.compactmods.feather.tests;

import dev.compactmods.feather.edge.GraphEdgeLookupFunction;
import dev.compactmods.feather.node.GraphNodeStream;
import dev.compactmods.feather.tests.example.NamedBlockPositionNode;
import dev.compactmods.feather.tests.example.StringNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class MemGraphTests {

    static final Predicate<StringNode> NODE_1_FILTER = n -> n.value().equals("test1");
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

        final StringNode node = graph.nodes(NODE_1_STREAM)
                .findFirst()
                .orElseThrow();

        Assertions.assertNotNull(node);
        Assertions.assertInstanceOf(StringNode.class, node);
        Assertions.assertEquals("test1", node.value());
    }

    @Test
    public void canLookupEdgesByType() throws Exception {
        final var graph = TestUtils.createBasicGraph(10);

        Assertions.assertNotNull(graph);

        final var matching = graph.edges(STRING_TO_STRING_LOOKUP)
                .toList();

        Assertions.assertTrue(!matching.isEmpty());

        final var firstResult = matching.get(0);

        StringNode target = firstResult.target().get();
        Assertions.assertNotNull(target);
        Assertions.assertInstanceOf(StringNode.class, target);
        Assertions.assertEquals("conn_2", target.value());
    }

    @Test
    public void example() throws Exception {
        final var graph = TestUtils.createBasicGraph(10);


        /*        (chest)
         * {LocalInventoryReader}---(BlockPos)---->[ConfigNode]
         *    - provides relative block pos
         */

        var namedPositionNode = NamedBlockPositionNode.create();

        namedPositionNode.dataStore()
                .get()
                .setName("This was changed!");

        var inputs = namedPositionNode.schema().inputNames().toList();
        var outputs = namedPositionNode.schema().outputNames().toList();

        var firstSchema = namedPositionNode.schema().schema(inputs.get(0));
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
