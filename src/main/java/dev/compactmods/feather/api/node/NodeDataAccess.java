package dev.compactmods.feather.api.node;

import dev.compactmods.feather.api.property.PropertyDataStore;

public interface NodeDataAccess<TNode> {
    NodeDataSchema<TNode> schema();

    PropertyDataStore<TNode> storage();
}