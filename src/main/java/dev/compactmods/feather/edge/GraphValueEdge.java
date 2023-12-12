package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

public interface GraphValueEdge<SN extends Node<?>, TN extends Node<?>, E> extends GraphEdge<SN, TN> {

    E value();
}
