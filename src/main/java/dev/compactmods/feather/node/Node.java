package dev.compactmods.feather.node;

import java.util.UUID;

public interface Node<T> {

    UUID id();

    T data();

    interface Mutable<T> extends Node<T> {
        void setData(T data);
    }
}