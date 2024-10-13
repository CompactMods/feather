package dev.compactmods.feather.api.node.stream;

import dev.compactmods.feather.api.graph.ReadableNodeSystem;

import java.util.stream.Stream;

@FunctionalInterface
public interface NodeStreamFunction<NodeKey> {

    Stream<NodeKey> apply(ReadableNodeSystem<NodeKey> graph);
}
