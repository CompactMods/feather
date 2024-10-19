package dev.compactmods.feather.tests.schema;

import dev.compactmods.feather.core.node.NodePropertySet;
import dev.compactmods.feather.core.node.property.PropertySchema;
import dev.compactmods.feather.property.SimplePropertySchema;
import dev.compactmods.feather.node.NodePropertySetBuilder;

public record NamedBlockPositionNode<NodeKey>(NodeKey key) {

    public static final PropertySchema<Long> BLOCK_POS = SimplePropertySchema.required(0L);

    public static final NodePropertySet SCHEMA = new NodePropertySetBuilder()
            .addProperties(TestNodeProperties.OPTIONAL_NAME)
            .addProperties("position", BLOCK_POS)
            .build();
}
