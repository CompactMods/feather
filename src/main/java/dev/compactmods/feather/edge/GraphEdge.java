package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

import java.lang.ref.WeakReference;

public interface GraphEdge<S, SN extends Node<S>, T, TN extends Node<T>> {

    WeakReference<SN> source();

    WeakReference<TN> target();

}
