package dev.compactmods.feather.api.node;

public interface DataHoldingNode<S> extends Node {
    NodePropertySet getSchema();

    NodeDataAccess dataAccess();
}
