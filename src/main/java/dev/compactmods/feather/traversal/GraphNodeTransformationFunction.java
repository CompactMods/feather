package dev.compactmods.feather.traversal;

import dev.compactmods.feather.MemoryGraph;

@FunctionalInterface
public interface GraphNodeTransformationFunction<ON, Out> {
    Out apply(MemoryGraph graph, ON input);
}
