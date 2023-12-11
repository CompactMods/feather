package dev.compactmods.feather.node;

import dev.compactmods.feather.MemoryGraph;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphAdjacentNodeStream<Origin extends Node, Destination extends Node> {

    Stream<Destination> nodes(MemoryGraph graph, Origin originNode);
}
