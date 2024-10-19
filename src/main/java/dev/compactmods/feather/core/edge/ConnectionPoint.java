package dev.compactmods.feather.core.edge;

/**
 * Represents a connection point on some object in a node system.
 * Edges must connect to these, and they may be either a single point or multipoint connection.
 */
public interface ConnectionPoint<TParent> {
    TParent parent();

    default boolean allowMultiple() {
        return false;
    }
}
