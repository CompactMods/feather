package dev.compactmods.feather.connection;

public record NodeConnection<T>(String name, Class<T> type) {
}