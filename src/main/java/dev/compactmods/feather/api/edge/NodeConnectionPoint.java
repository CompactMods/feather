package dev.compactmods.feather.api.edge;

import dev.compactmods.feather.api.node.Node;

/**
 * Represents a connection point in a node graph.
 * Edges must connect to these, and they may be either a single point or multipoint connection.
 */
public interface NodeConnectionPoint {
    Node node();
}
