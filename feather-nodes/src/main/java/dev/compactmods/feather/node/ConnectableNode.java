package dev.compactmods.feather.node;

import dev.compactmods.feather.feature.connections.ConnectionPoint;
import dev.compactmods.feather.node.property.Property;

/**
 * Marks a node as being able to take part in a node connection graph.
 */
public interface ConnectableNode {
    ConnectionPoint<Node> connector(Property<?> property);
}
