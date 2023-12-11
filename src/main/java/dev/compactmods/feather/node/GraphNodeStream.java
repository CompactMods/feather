package dev.compactmods.feather.node;

import dev.compactmods.feather.graph.NodeAccessor;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphNodeStream<T extends Node<?>> {

    Stream<T> apply(NodeAccessor graph);
}
