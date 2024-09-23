package dev.compactmods.feather.api.property;

import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.api.node.NodeDataSchema;

import java.util.Optional;

public interface PropertyDataStore<T> {
    NodeDataAccess<T> makeAccess();

    <P> boolean valueMatches(Property<P> property, P value);

    <P> Optional<P> get(Property<P> property);
    <P> void set(Property<P> property, P value);

    <TNode> Optional<PropertyDataStore<TNode>> tryCast(NodeDataSchema<TNode> schema);
}
