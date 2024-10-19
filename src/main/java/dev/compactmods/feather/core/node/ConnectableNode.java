package dev.compactmods.feather.core.node;

import dev.compactmods.feather.core.edge.ConnectionPoint;
import dev.compactmods.feather.core.node.property.Property;

/**
 * Marks a node as being able to take part in a node connection graph.
 */
public interface ConnectableNode {
    ConnectionPoint<Node> connector(Property<?> property);
}
