package dev.compactmods.feather.api.node;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.api.edge.ConnectionPoint;
import dev.compactmods.feather.api.property.Property;

/**
 * Marks a node as being able to take part in a node connection graph.
 */
public interface ConnectableNode {
    ConnectionPoint<Node> connector(Property<?> property);
}
