package dev.compactmods.feather.traversal;

import dev.compactmods.feather.Graph;

@FunctionalInterface
public interface GraphNodeTransformationFunction<ON, Out> {
    Out apply(Graph graph, ON input);
}
