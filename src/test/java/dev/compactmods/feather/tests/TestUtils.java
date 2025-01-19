package dev.compactmods.feather.tests;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.core.feature.NodeFeatureManager;
import dev.compactmods.feather.feature.BasicNodeFeatures;
import dev.compactmods.feather.core.feature.NodeFeatureInstance;
import dev.compactmods.feather.node.stream.NodeStreamFunction;
import dev.compactmods.feather.tests.schema.StringNode;
import dev.compactmods.feather.tests.schema.TestNodeProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class TestUtils {

    public static NodeSystem<UUID> createBasicGraph(int numNodes) {
        final var g = new NodeSystem<>(UUID::randomUUID);



        final var conn1 = makeStringNodeWithValue(g, "conn_1");
        final var conn2 = makeStringNodeWithValue(g, "conn_2");
        // g.connectNodes(conn1, conn2);

        for (var i = 1; i < numNodes + 1; i++) {
            makeStringNodeWithValue(g, "Test Node " + i);
        }

        return g;
    }

    private static UUID makeStringNodeWithValue(NodeSystem<UUID> g, String value) {
        final var instance = new StringNode(value, new NodeFeatureManager(StringNode.SCHEMA));
        final var newNodeId = g.addNode(instance);

        try {
            var feats = g.featureManager().nodeFeatures(newNodeId);
            Objects.requireNonNull(feats);

            var data = feats.getFeature(BasicNodeFeatures.PROPERTY_DATA_STORE);
            Objects.requireNonNull(data);

            data.set(TestNodeProperties.OPTIONAL_STRING_VALUE, value);
        }

        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return newNodeId;
    }

    public static @NotNull NodeStreamFunction<UUID> makeStringNodeNameLookup(String name) {
        return nodeSystem -> nodeSystem.featureManager()
                .nodesWithFeature(BasicNodeFeatures.PROPERTY_DATA_STORE)
                .filter((nf) -> {
                    final var dataStore = nf.feature();
                    return dataStore.valueMatches(TestNodeProperties.OPTIONAL_STRING_VALUE, name);
                })
                .map(NodeFeatureInstance::nodeID);
    }
}
