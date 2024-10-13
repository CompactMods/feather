package dev.compactmods.feather.api.edge;

import java.lang.ref.WeakReference;

public interface DirectedEdge<TConnectType, TConnectionPoint extends ConnectionPoint<TConnectType>> {
    WeakReference<TConnectionPoint> source();
    WeakReference<TConnectionPoint> target();
}
