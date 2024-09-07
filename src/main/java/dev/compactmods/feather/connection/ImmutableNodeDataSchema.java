package dev.compactmods.feather.connection;

import java.util.function.Function;

public record ImmutableNodeDataSchema<DataHolder, T>(T initialValue,
                                                     Function<DataHolder, T> reader,
                                                     boolean isOptional,
                                                     boolean isInput, boolean isOutput)
        implements NodeDataSchema<T> {

}
