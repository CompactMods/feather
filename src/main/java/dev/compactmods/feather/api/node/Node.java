package dev.compactmods.feather.api.node;

public interface Node<NodeKey> {
    NodeSchema<NodeKey> schema();
}
