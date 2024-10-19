package dev.compactmods.feather.core.feature;

import dev.compactmods.feather.NodeSystem;

import java.util.UUID;

@FunctionalInterface
public interface Node2FeatureTransformFunc {

    NodeFeature apply(NodeSystem system, UUID stream);
}
