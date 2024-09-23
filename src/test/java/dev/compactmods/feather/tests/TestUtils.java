package dev.compactmods.feather.tests;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.tests.example.StringNode;

import java.util.UUID;

public class TestUtils {

    public static NodeSystem createBasicGraph(int numNodes) {
        final var g = new NodeSystem();

        final var conn1 = makeStringNodeWithValue(g, "conn_1");
        final var conn2 = makeStringNodeWithValue(g, "conn_2");
        // g.connectNodes(conn1, conn2);

        for (var i = 1; i < numNodes + 1; i++) {
            makeStringNodeWithValue(g, "Test Node " + i);
        }

        return g;
    }

    private static UUID makeStringNodeWithValue(NodeSystem g, String value) {
        final var newNodeId = g.addNode(StringNode.SCHEMA);
        g.nodeData(newNodeId).storage().set(StringNode.VALUE, value);
        return newNodeId;
    }
}
