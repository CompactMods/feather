package dev.compactmods.feather.tests;

import dev.compactmods.feather.node.Node;

import java.util.Objects;
import java.util.UUID;

public class ScalarValueNode<T> implements Node<T> {

    private final UUID id;
    private T data;

    public ScalarValueNode(UUID id, T data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ScalarValueNode) obj;
        return Objects.equals(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "ScalarValueNode[" +
                "data=" + data + ']';
    }

}
