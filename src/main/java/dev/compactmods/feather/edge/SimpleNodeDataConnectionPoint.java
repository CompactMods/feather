package dev.compactmods.feather.edge;

import dev.compactmods.feather.api.edge.NodeDataConnectionPoint;
import dev.compactmods.feather.api.node.Node;

import java.lang.ref.WeakReference;

public record SimpleNodeDataConnectionPoint<TDataType>(WeakReference<Node> nodeRef, Class<TDataType> dataType)
        implements NodeDataConnectionPoint<TDataType> {
    @Override
    public Node node() {
        return nodeRef.get();
    }
}