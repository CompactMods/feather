package dev.compactmods.feather.connection;

import java.util.function.Function;

public interface NodeDataSchema<T> {
    T initialValue();
    boolean isOptional();
    boolean isInput();
    boolean isOutput();
}
