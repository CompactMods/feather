package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.node.Node;
import dev.compactmods.feather.node.schema.NodeSchema;

import java.util.UUID;

public record StringNode(UUID id, MutableStorage<String> dataStore) implements Node<String> {

    private static final SimpleNodeSchema<String> SCHEMA = new SimpleNodeSchema<>("");

    public StringNode(UUID id, String initialValue) {
        this(id, new MutableStorage<>(initialValue));
    }

    @Override
    public NodeSchema<String> schema() {
        return SCHEMA;
    }

    public String value() {
        return dataStore.get();
    }
}
