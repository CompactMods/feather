package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.node.data.NodeDataStore;

public class MutableStorage<T> implements NodeDataStore.Mutable<T> {
    private T data;

    public MutableStorage(T initialValue) {
        this.data = initialValue;
    }

    @Override
    public void overwrite(T value) {
        this.data = value;
    }

    @Override
    public T get() {
        return this.data;
    }
}
