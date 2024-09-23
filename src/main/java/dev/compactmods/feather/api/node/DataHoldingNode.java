package dev.compactmods.feather.api.node;

public interface DataHoldingNode<S> extends Node {
    NodeDataSchema<S> getSchema();

    NodeDataAccess<S> dataAccess();
}
