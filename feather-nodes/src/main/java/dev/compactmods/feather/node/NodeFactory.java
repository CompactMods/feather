package dev.compactmods.feather.node;

@FunctionalInterface
public interface NodeFactory<TNodeType, NodeKey> {

    TNodeType create(NodeKey id);
}
