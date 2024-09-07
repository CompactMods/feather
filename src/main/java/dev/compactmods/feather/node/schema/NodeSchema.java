package dev.compactmods.feather.node.schema;

import dev.compactmods.feather.connection.NodeDataSchema;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface NodeSchema<DataHolder> extends NodeDataSchemaProvider {
    static <T> NodeSchema.Builder<T> builder() {
        return new Builder<T>();
    }

    Stream<String> inputNames();

    Stream<String> outputNames();

    int connectorCount();

    default Optional<NodeDataSchema<?>> schema(String name) {
        return Optional.empty();
    }

    class Builder<T> {
        private final Map<String, NodeDataSchema<?>> properties = new HashMap<>();

        public <DataType> Builder<T> addData(String name, DataType initialValue, UnaryOperator<NodeDataSchemaBuilder<T, DataType>> builder) {
            var schema = builder.apply(new NodeDataSchemaBuilder<>(initialValue));
            properties.put(name, schema.build());
            return this;
        }

        public NodeSchema<T> build() {
            return new ComposedNodeSchema<>(properties);
        }

    }
}
