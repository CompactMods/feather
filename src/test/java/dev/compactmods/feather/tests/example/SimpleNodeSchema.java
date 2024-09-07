package dev.compactmods.feather.tests.example;

import com.google.common.reflect.TypeToken;
import dev.compactmods.feather.connection.MutableNodeDataSchema;
import dev.compactmods.feather.connection.NodeDataSchema;
import dev.compactmods.feather.node.schema.NodeDataSchemaProvider;
import dev.compactmods.feather.node.schema.NodeSchema;

import java.util.Optional;
import java.util.stream.Stream;

public final class SimpleNodeSchema<DataType> implements NodeSchema<DataType>, NodeDataSchemaProvider.Typed<DataType> {

    @SuppressWarnings("UnstableApiUsage")
    private final TypeToken<DataType> type = new TypeToken<DataType>(getClass()) {
    };
    private final MutableNodeDataSchema<MutableStorage<DataType>, DataType> schema;

    public SimpleNodeSchema(DataType initialValue) {
        this.schema = new MutableNodeDataSchema<>(initialValue, MutableStorage::get, MutableStorage::overwrite, true, true, true);
    }

    @Override
    public Stream<String> inputNames() {
        return Stream.of("value");
    }

    @Override
    public Stream<String> outputNames() {
        return Stream.of("value");
    }

    @Override
    public Optional<NodeDataSchema<DataType>> typedSchema(String name) {
        if (!name.equals("value")) return Optional.empty();
        return Optional.of(schema);
    }

    @Override
    public int connectorCount() {
        return 1;
    }

}
