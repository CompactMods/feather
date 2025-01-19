package dev.compactmods.feather.node;

import dev.compactmods.feather.core.feature.NodeFeatureManager;

public interface Node {
    NodeSchema schema();
    NodeFeatureManager features();
}
