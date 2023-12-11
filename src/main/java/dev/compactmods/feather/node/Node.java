package dev.compactmods.feather.node;

import java.util.UUID;

public interface Node<T> {

    UUID id();

    T data();

    void setData(T data);

}