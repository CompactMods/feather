package dev.compactmods.feather.node;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphNodeStream<T extends Node<?>> {

    Stream<T> apply(NodeAccessor graph);
}
