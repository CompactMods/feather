package dev.compactmods.feather.core.node.stream;

import dev.compactmods.feather.core.graph.ReadableNodeSystem;

import java.util.stream.Stream;

@FunctionalInterface
public interface NodeStreamFunction<NodeKey> {

    Stream<NodeKey> apply(ReadableNodeSystem<NodeKey> graph);
}
