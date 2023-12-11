package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

public interface GraphValueEdge<E, S, SN extends Node<S>, T, TN extends Node<T>> extends GraphEdge<S, SN, T, TN> {

    E value();
}