package dev.compactmods.feather.tests.benchmarks;

import dev.compactmods.feather.tests.TestUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class MemoryGraphBenchmarks {

//    @Benchmark
    public void createBasicGraph(Blackhole bh) throws Exception {
        final var graph = TestUtils.createBasicGraph(1);
        bh.consume(graph);
    }
}
