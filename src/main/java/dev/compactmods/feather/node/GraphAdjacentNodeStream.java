package dev.compactmods.feather.node;

import dev.compactmods.feather.Graph;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphAdjacentNodeStream<Origin extends Node, Destination extends Node> {

    Stream<Destination> nodes(Graph graph, Origin originNode);
}
