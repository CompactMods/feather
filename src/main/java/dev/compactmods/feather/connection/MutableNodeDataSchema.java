package dev.compactmods.feather.connection;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record MutableNodeDataSchema<DataHolder, T>(T initialValue,
                                                   Function<DataHolder, T> reader,
                                                   BiConsumer<DataHolder, T> writer,
                                                   boolean isOptional,
                                                   boolean isInput, boolean isOutput)
        implements NodeDataSchema<T> {
}
