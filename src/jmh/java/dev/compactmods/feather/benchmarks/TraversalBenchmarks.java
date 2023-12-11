package dev.compactmods.feather.benchmarks;

import dev.compactmods.feather.MemoryGraph;
import dev.compactmods.feather.tests.TestUtils;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
public class TraversalBenchmarks {

    @Param({ "1", "10", "100", "10000", "50000" })
    public int numNodes;

    public int randomIndex;

    public MemoryGraph graph;

    @Setup(Level.Invocation)
    public void setupBenchmarks() throws Exception {
        graph = TestUtils.createBasicGraph(numNodes);
        randomIndex = ThreadLocalRandom.current().nextInt(1, numNodes + 1);
    }

//    @Benchmark
//    public void bmNodeByLookup(Blackhole blackhole) {
//        final var node = graph.nodes(g -> g.nodes().stream().filter(n -> n instanceof ScalarValueNode<?> svn && svn.value().equals("test1")))
//                .findFirst()
//                .orElseThrow();
//
//        blackhole.consume(node);
//    }
//
//    @Benchmark
//    public void bmNodeByCache(Blackhole blackhole) {
//        final var node = graph.nodes(ScalarValueNode.class, "Test Node " + randomIndex).orElseThrow();
//        blackhole.consume(node);
//    }
//
////    @Benchmark
//    public void canTraverseEdgesByFunction(Blackhole blackhole) {
//        final var node = graph.nodes(String.class, "conn_1").orElseThrow();
//        final GraphEdgeLookupFunction<ScalarValueNode<String>, EmptyEdge, ScalarValueNode<String>> lookupConnectedTo = EdgeLookups.connectedTo(EmptyEdge.class, node);
//        final var connectedToTestNode = graph.edgeStream(lookupConnectedTo).toList();
//        final var firstResult = connectedToTestNode.get(0);
//
//        blackhole.consume(firstResult);
//    }
}
