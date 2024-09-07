package dev.compactmods.feather.node;

import dev.compactmods.feather.node.schema.NodeSchema;

import java.util.UUID;

public interface Node<T> {
    UUID id();
    NodeSchema<T> schema();
}