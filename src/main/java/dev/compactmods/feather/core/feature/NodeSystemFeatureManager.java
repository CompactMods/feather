package dev.compactmods.feather.core.feature;

import java.util.stream.Stream;

public interface NodeSystemFeatureManager<NodeKey> {
    <T> Stream<NodeFeatureInstance<NodeKey, T>> nodesWithFeature(NodeFeature<T> feature);

    NodeFeatureManager<NodeKey> nodeFeatures(NodeKey nid);
}
