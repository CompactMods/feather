package dev.compactmods.feather.tests.junit;

import dev.compactmods.feather.api.feature.BasicNodeFeatures;
import dev.compactmods.feather.api.feature.NodeFeatureManager;
import dev.compactmods.feather.api.node.stream.GraphNodeStream;
import dev.compactmods.feather.tests.TestUtils;
import dev.compactmods.feather.tests.junit.example.TestNodeProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

public class NodeSystemTests {

    static final GraphNodeStream<UUID> NODE_BY_NAME_LOOKUP = (g) -> g.nodesWithFeature(BasicNodeFeatures.DATA_HOST)
            .filter((nodeID) -> {
                var feat = g.nodeFeatures(nodeID).getFeature(BasicNodeFeatures.DATA_HOST);
                return feat.valueMatches(TestNodeProperties.OPTIONAL_STRING_VALUE, "Test Node 1");
            });

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

        final var featureManager = NODE_BY_NAME_LOOKUP.apply(graph)
                .map(graph::nodeFeatures)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow();

        Assertions.assertNotNull(featureManager);
        Assertions.assertInstanceOf(NodeFeatureManager.class, featureManager);

        var dataStore = featureManager.getFeature(BasicNodeFeatures.DATA_HOST);
        Assertions.assertNotNull(dataStore);
        Assertions.assertEquals("Test Node 1", dataStore.get(TestNodeProperties.OPTIONAL_STRING_VALUE).orElseThrow());
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
