package dev.compactmods.feather.traversal;

import dev.compactmods.feather.MemoryGraph;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphNodeStreamFunction<ON, Out> {

    Stream<Out> apply(MemoryGraph graph, ON input);
}
