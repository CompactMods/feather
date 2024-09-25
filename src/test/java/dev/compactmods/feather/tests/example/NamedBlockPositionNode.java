package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.api.node.NodePropertySet;
import dev.compactmods.feather.api.property.PropertySchema;
import dev.compactmods.feather.property.SimplePropertySchema;
import dev.compactmods.feather.node.NodePropertySetBuilder;

public class NamedBlockPositionNode {

    public static final PropertySchema<Long> BLOCK_POS = SimplePropertySchema.required(0L);

    public static final NodePropertySet SCHEMA = new NodePropertySetBuilder()
            .addProperties(TestNodeProperties.OPTIONAL_NAME)
            .addProperties("position", BLOCK_POS)
            .build();
}
