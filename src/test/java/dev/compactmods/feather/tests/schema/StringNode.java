package dev.compactmods.feather.tests.schema;

import dev.compactmods.feather.core.feature.NodeFeatureManager;
import dev.compactmods.feather.core.node.Node;
import dev.compactmods.feather.core.node.NodePropertySet;
import dev.compactmods.feather.core.node.NodeSchema;
import dev.compactmods.feather.feature.BasicNodeFeatures;
import dev.compactmods.feather.node.NodePropertySetBuilder;
import dev.compactmods.feather.node.NodeSchemaBuilder;
import dev.compactmods.feather.property.SimplePropertyDataStore;

public record StringNode(String value, NodeFeatureManager features) implements Node {

    static final NodePropertySet PROPERTIES = new NodePropertySetBuilder()
            .addProperties(TestNodeProperties.OPTIONAL_STRING_VALUE)
            .build();

    public static final NodeSchema SCHEMA = new NodeSchemaBuilder()
            .registerFeature(BasicNodeFeatures.PROPERTY_DATA_STORE, (o) -> new SimplePropertyDataStore(PROPERTIES))
            .build();

    @Override
    public NodeSchema schema() {
        return SCHEMA;
    }
}
