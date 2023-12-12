package dev.compactmods.feather.edge;

import dev.compactmods.feather.node.Node;

import java.lang.ref.WeakReference;

public interface GraphEdge<SN extends Node<?>, TN extends Node<?>> {

    WeakReference<SN> source();

    WeakReference<TN> target();

}
