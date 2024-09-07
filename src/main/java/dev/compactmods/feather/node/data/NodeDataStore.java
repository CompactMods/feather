package dev.compactmods.feather.node.data;

public interface NodeDataStore<T> {
    T get();

    interface Mutable<T> extends NodeDataStore<T> {
        void overwrite(T value);
    }
}
