package dev.compactmods.feather.api.edge;

import java.lang.ref.WeakReference;

public interface DirectedEdge<S extends NodeConnectionPoint, T extends NodeConnectionPoint> {
    WeakReference<S> source();
    WeakReference<T> target();
}
