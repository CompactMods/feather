package dev.compactmods.feather.tests.schema;

import dev.compactmods.feather.core.feature.NodeFeatureManager;
import dev.compactmods.feather.core.node.Node;
import dev.compactmods.feather.core.node.NodePropertySet;
import dev.compactmods.feather.core.node.NodeSchema;
import dev.compactmods.feather.node.property.PropertySchema;
import dev.compactmods.feather.feature.BasicNodeFeatures;
import dev.compactmods.feather.node.NodeSchemaBuilder;
import dev.compactmods.feather.property.SimplePropertyDataStore;
import dev.compactmods.feather.property.SimplePropertySchema;
import dev.compactmods.feather.node.NodePropertySetBuilder;

public record NamedBlockPositionNode<NodeKey>(NodeKey key) implements Node {

    public static final PropertySchema<Long> BLOCK_POS = SimplePropertySchema.required(0L);

    public static final NodePropertySet DATA_SCHEMA = new NodePropertySetBuilder()
            .addProperties(TestNodeProperties.OPTIONAL_NAME)
            .addProperties("position", BLOCK_POS)
            .build();

    public static final NodeSchema NODE_SCHEMA = new NodeSchemaBuilder()
            .registerFeature(BasicNodeFeatures.PROPERTY_DATA_STORE, (o) -> new SimplePropertyDataStore(DATA_SCHEMA))
            .build();

    @Override
    public NodeSchema schema() {
        return NODE_SCHEMA;
    }

    @Override
    public NodeFeatureManager features() {
        return null;
    }
}
