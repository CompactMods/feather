package dev.compactmods.feather.edge.impl;

import dev.compactmods.feather.edge.GraphEdge;
import dev.compactmods.feather.node.Node;

import java.lang.ref.WeakReference;

public record EmptyEdge<SD, S extends Node<SD>, TD, T extends Node<TD>>(WeakReference<S> source, WeakReference<T> target) implements GraphEdge<S, T> {

    public EmptyEdge(S source, T target) {
        this(new WeakReference<>(source), new WeakReference<>(target));
    }

}
