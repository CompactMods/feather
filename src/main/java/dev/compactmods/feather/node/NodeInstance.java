package dev.compactmods.feather.node;

import dev.compactmods.feather.node.schema.NodeSchema;

import java.util.UUID;

public record NodeInstance<T>(UUID id, NodeSchema<T> schema) implements Node<T> {
}
