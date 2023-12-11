package dev.compactmods.feather.tests;

import dev.compactmods.feather.node.Node;

import java.util.UUID;

public class StringNode extends ScalarValueNode<String> {
    public StringNode(UUID id, String data) {
        super(id, data);
    }
}
