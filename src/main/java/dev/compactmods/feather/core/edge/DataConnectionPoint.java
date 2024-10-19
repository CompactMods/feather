package dev.compactmods.feather.core.edge;

import dev.compactmods.feather.edge.DirectedConnectionManager;

/**
 * A connection point for forming {@link DirectedDataEdge graph edges} in a
 * {@link DirectedConnectionManager direction node graph}.
 *
 * @param <TDataType>
 */
public interface DataConnectionPoint<TParent, TDataType> extends ConnectionPoint<TParent> {
    Class<TDataType> dataType();
}
