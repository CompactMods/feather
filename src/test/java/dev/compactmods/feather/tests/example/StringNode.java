package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.api.node.DataHoldingNode;
import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.api.property.Property;
import dev.compactmods.feather.node.DataNodeSchemaBuilder;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.property.NamedProperty;
import dev.compactmods.feather.property.SimplePropertyDataStore;

public record StringNode(SimplePropertyDataStore<StringNode> propertyStore) implements DataHoldingNode<StringNode> {

    public static final Property<String> VALUE = new NamedProperty<>("value", BasicPropertySchemas.STRING);

    public static final NodeDataSchema<StringNode> SCHEMA = new DataNodeSchemaBuilder<>(StringNode.class)
            .addProperties(VALUE)
            .build();

    public static StringNode create() {
        return new StringNode(new SimplePropertyDataStore<>(SCHEMA));
    }

    @Override
    public NodeDataSchema<StringNode> getSchema() {
        return SCHEMA;
    }

    @Override
    public NodeDataAccess<StringNode> dataAccess() {
        return propertyStore.makeAccess();
    }
}
