package dev.compactmods.feather.core.edge;

public interface DirectedDataEdge<TParent, TDataType, TDataConnectionType extends DataConnectionPoint<TParent, TDataType>>
    extends DirectedEdge<TParent, TDataConnectionType> { }
