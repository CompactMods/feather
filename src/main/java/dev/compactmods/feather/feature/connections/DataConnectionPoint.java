package dev.compactmods.feather.feature.connections;

/**
 * A connection point for forming {@link DirectedDataEdge graph edges} in a
 * {@link DirectedConnectionManager direction node graph}.
 *
 * @param <TDataType>
 */
public interface DataConnectionPoint<TParent, TDataType> extends ConnectionPoint<TParent> {
    Class<TDataType> dataType();
}
