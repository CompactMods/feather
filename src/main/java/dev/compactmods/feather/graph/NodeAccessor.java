package dev.compactmods.feather.graph;

import dev.compactmods.feather.node.Node;

import java.util.stream.Stream;

public interface NodeAccessor {

    Stream<Node<?>> nodes();

    <T extends Node<?>> Stream<T> nodes(Class<T> c);

    <T extends Node<?>> Stream<Node<?>> predecessors(T sourceNode);

    <T extends Node<?>> Stream<Node<?>> successors(T sourceNode);
}
