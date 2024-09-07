package dev.compactmods.feather.traversal;

import dev.compactmods.feather.Graph;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphNodeStreamFunction<ON, Out> {

    Stream<Out> apply(Graph graph, ON input);
}
