package dev.compactmods.feather.tests.benchmarks;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.tests.TestUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Fork(1)
@Threads(10)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 5)
public class TraversalBenchmarks {

    @Param({"5000", "10000", "50000"})
    public int numNodes;

    public int randomIndex;

    public NodeSystem<UUID> graph;

    @Setup(Level.Invocation)
    public void setupBenchmarks() {
        graph = TestUtils.createBasicGraph(numNodes);
        randomIndex = ThreadLocalRandom.current().nextInt(1, numNodes + 1);
    }

    @Benchmark
    public void bmNodeByLookup(Blackhole blackhole) {
        var foundID = TestUtils.makeStringNodeNameLookup("Test Node " + randomIndex)
                .apply(graph)
                .findFirst()
                .orElseThrow();

        blackhole.consume(foundID);
    }
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
