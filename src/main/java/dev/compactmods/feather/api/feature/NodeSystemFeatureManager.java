package dev.compactmods.feather.api.feature;

import it.unimi.dsi.fastutil.Pair;

import java.util.stream.Stream;

public interface NodeSystemFeatureManager<NodeKey> {
    <T> Stream<NodeFeatureInstance<NodeKey, T>> nodesWithFeature(NodeFeature<T> feature);

    NodeFeatureManager<NodeKey> nodeFeatures(NodeKey nid);
}
