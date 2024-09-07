package dev.compactmods.feather.node.schema;

import dev.compactmods.feather.connection.NodeDataSchema;

import java.util.Optional;

public interface NodeDataSchemaProvider {
    Optional<NodeDataSchema<?>> schema(String name);

    interface Typed<DataType> {
        Optional<NodeDataSchema<DataType>> typedSchema(String name);
    }
}

