package dev.compactmods.feather.core.node;

@FunctionalInterface
public interface NodeFactory<TNodeType, NodeKey> {

    TNodeType create(NodeKey id);
}
