package dev.compactmods.feather.traversal;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.api.node.Node;
import dev.compactmods.feather.api.property.PropertyDataStore;

import java.util.stream.Stream;

@FunctionalInterface
public interface GraphNodeDataStreamFunction {

    Stream<PropertyDataStore> apply(NodeSystem graph, Node input);
}
