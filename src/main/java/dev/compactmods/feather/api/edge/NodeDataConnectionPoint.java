package dev.compactmods.feather.api.edge;

import dev.compactmods.feather.edge.DirectedConnectionManager;

/**
 * A connection point for forming {@link DirectedDataEdge graph edges} in a
 * {@link DirectedConnectionManager direction node graph}.
 *
 * @param <TDataType>
 */
public interface NodeDataConnectionPoint<TDataType> extends NodeConnectionPoint {
    Class<TDataType> dataType();
}
