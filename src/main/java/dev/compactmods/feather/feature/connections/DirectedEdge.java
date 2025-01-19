package dev.compactmods.feather.feature.connections;

import java.lang.ref.WeakReference;

public interface DirectedEdge<TConnectType, TConnectionPoint extends ConnectionPoint<TConnectType>> {
    WeakReference<TConnectionPoint> source();
    WeakReference<TConnectionPoint> target();
}
