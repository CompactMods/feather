package dev.compactmods.feather.tests.schema;

import dev.compactmods.feather.core.node.NodeFactory;

import java.util.UUID;

public class ExampleNodeSystem {

    public static final NodeFactory<NamedBlockPositionNode<UUID>, UUID> FACTORY = NamedBlockPositionNode::new;
}
