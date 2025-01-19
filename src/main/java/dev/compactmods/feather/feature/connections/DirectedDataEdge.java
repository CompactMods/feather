package dev.compactmods.feather.feature.connections;

public interface DirectedDataEdge<TParent, TDataType, TDataConnectionType extends DataConnectionPoint<TParent, TDataType>>
    extends DirectedEdge<TParent, TDataConnectionType> { }
