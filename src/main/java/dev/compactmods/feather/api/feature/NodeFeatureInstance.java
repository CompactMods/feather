package dev.compactmods.feather.api.feature;

public record NodeFeatureInstance<NodeKey, T>(NodeKey nodeID, T feature) {
}
