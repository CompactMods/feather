package dev.compactmods.feather.tests;

import dev.compactmods.feather.MemoryGraph;

import java.util.UUID;

public class TestUtils {

    public static MemoryGraph createBasicGraph(int numNodes) {
        final var g = new MemoryGraph();

        final var conn1 = g.addNode(new StringNode(UUID.randomUUID(), "conn_1"));
        final var conn2 = g.addNode(new StringNode(UUID.randomUUID(), "conn_2"));
        g.connectNodes(conn1, conn2);

        g.addNode(new StringNode(UUID.randomUUID(), "test1"));
        g.addNode(new StringNode(UUID.randomUUID(), "test2"));

        for(var i = 1; i < numNodes + 1; i++)
            g.addNode(new StringNode(UUID.randomUUID(), "Test Node " + i));

        return g;
    }
}
