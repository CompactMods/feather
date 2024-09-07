package dev.compactmods.feather.node.schema;

import dev.compactmods.feather.connection.ImmutableNodeDataSchema;
import dev.compactmods.feather.connection.MutableNodeDataSchema;
import dev.compactmods.feather.connection.NodeDataSchema;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class NodeDataSchemaBuilder<DataStorage, DataType> {

    private final DataType initialValue;
    private boolean isOptional = false;
    private boolean isInput = false;
    private boolean isOutput = false;
    private Function<DataStorage, DataType> reader;
    private BiConsumer<DataStorage, DataType> writer;

    public NodeDataSchemaBuilder(DataType initialValue) {
        this.initialValue = initialValue;
    }

    public NodeDataSchemaBuilder<DataStorage, DataType> optional() {
        this.isOptional = true;
        return this;
    }

    public NodeDataSchemaBuilder<DataStorage, DataType> input() {
        this.isInput = true;
        return this;
    }

    public NodeDataSchemaBuilder<DataStorage, DataType> output() {
        this.isOutput = true;
        return this;
    }

    public NodeDataSchemaBuilder<DataStorage, DataType> readable(Function<DataStorage, DataType> func) {
        this.reader = func;
        return this;
    }

    public NodeDataSchemaBuilder<DataStorage, DataType> modifiable(BiConsumer<DataStorage, DataType> func) {
        this.writer = func;
        return this;
    }

    public NodeDataSchema<DataType> build() {
        if (reader == null)
            throw new RuntimeException();

        if (writer == null)
            return new ImmutableNodeDataSchema<>(initialValue, reader, isOptional, isInput, isOutput);

        return new MutableNodeDataSchema<>(initialValue, reader, writer, isOptional, isInput, isOutput);
    }
}
