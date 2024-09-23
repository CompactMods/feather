package dev.compactmods.feather.api.graph;

import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.api.node.NodeDataSchema;

import java.util.UUID;
import java.util.stream.Stream;

public interface NodeAccess {
    Stream<UUID> nodeIDs();

    <T> NodeDataAccess<T> nodeData(UUID nodeID);

    <TNode, TSchema extends NodeDataSchema<TNode>> Stream<UUID> nodesByDataSchema(TSchema schema);
}
