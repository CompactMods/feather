package dev.compactmods.feather.api.edge;

public interface DirectedDataEdge<TDataType, SConn extends NodeDataConnectionPoint<TDataType>, TConn extends NodeDataConnectionPoint<TDataType>>
    extends DirectedEdge<SConn, TConn> { }
