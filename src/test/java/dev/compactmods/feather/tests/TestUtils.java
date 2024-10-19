package dev.compactmods.feather.tests;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.feature.BasicNodeFeatures;
import dev.compactmods.feather.core.feature.NodeFeatureInstance;
import dev.compactmods.feather.core.node.NodeSchema;
import dev.compactmods.feather.core.node.stream.NodeStreamFunction;
import dev.compactmods.feather.node.NodePropertySetBuilder;
import dev.compactmods.feather.property.SimplePropertyDataStore;
import dev.compactmods.feather.tests.schema.TestNodeProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class TestUtils {

    public static NodeSystem<UUID> createBasicGraph(int numNodes) {
        final var g = new NodeSystem<>(UUID::randomUUID);

        final var stringNodePropertySet = new NodePropertySetBuilder()
                .addProperties(TestNodeProperties.OPTIONAL_STRING_VALUE)
                .build();

        final var stringSchema = g.addSchema(b -> {
            b.registerFeature(BasicNodeFeatures.PROPERTY_DATA_STORE, (nid, o) -> new SimplePropertyDataStore(stringNodePropertySet));
        });

        final var conn1 = makeStringNodeWithValue(g, stringSchema, "conn_1");
        final var conn2 = makeStringNodeWithValue(g, stringSchema, "conn_2");
        // g.connectNodes(conn1, conn2);

        for (var i = 1; i < numNodes + 1; i++) {
            makeStringNodeWithValue(g, stringSchema, "Test Node " + i);
        }

        return g;
    }

    private static UUID makeStringNodeWithValue(NodeSystem<UUID> g, NodeSchema<UUID> stringSchema, String value) {
        final var newNodeId = g.addNode(stringSchema);

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
