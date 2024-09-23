package dev.compactmods.feather.api.node.stream;

import dev.compactmods.feather.api.node.Node;
import dev.compactmods.feather.api.graph.NodeAccess;
import dev.compactmods.feather.api.property.PropertyDataStore;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphDataNodeStream<TNode extends Node> {

    Stream<PropertyDataStore<TNode>> apply(NodeAccess graph);
}
