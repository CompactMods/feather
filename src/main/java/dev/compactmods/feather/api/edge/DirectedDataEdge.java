package dev.compactmods.feather.api.edge;

public interface DirectedDataEdge<TParent, TDataType, TDataConnectionType extends DataConnectionPoint<TParent, TDataType>>
    extends DirectedEdge<TParent, TDataConnectionType> { }
