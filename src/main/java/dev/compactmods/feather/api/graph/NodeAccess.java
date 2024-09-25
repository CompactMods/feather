package dev.compactmods.feather.api.graph;

import dev.compactmods.feather.api.feature.NodeFeature;
import dev.compactmods.feather.api.feature.NodeFeatureManager;

import java.util.stream.Stream;

public interface NodeAccess<NodeKey> {
    Stream<NodeKey> nodeIDs();

    <T> Stream<NodeKey> nodesWithFeature(NodeFeature<T> feature);

    NodeFeatureManager<NodeKey> nodeFeatures(NodeKey nid);
}
