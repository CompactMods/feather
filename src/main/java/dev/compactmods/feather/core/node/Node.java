package dev.compactmods.feather.core.node;

public interface Node<NodeKey> {
    NodeSchema<NodeKey> schema();
}
