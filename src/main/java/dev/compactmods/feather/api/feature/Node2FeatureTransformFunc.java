package dev.compactmods.feather.api.feature;

import dev.compactmods.feather.NodeSystem;

import java.util.UUID;

@FunctionalInterface
public interface Node2FeatureTransformFunc {

    NodeFeature apply(NodeSystem system, UUID stream);
}
