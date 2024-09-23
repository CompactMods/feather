package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.api.edge.NodeConnectionPoint;
import dev.compactmods.feather.api.node.ConnectableNode;
import dev.compactmods.feather.api.node.DataHoldingNode;
import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.api.property.Property;
import dev.compactmods.feather.api.property.PropertySchema;
import dev.compactmods.feather.property.SimplePropertySchema;
import dev.compactmods.feather.property.SimplePropertyDataStore;
import dev.compactmods.feather.node.DataNodeSchemaBuilder;

public final class NamedBlockPositionNode implements DataHoldingNode<NamedBlockPositionNode>, ConnectableNode {

    public static final PropertySchema<Long> BLOCK_POS = SimplePropertySchema.required(0L);

    public static final NodeDataSchema<NamedBlockPositionNode> SCHEMA = new DataNodeSchemaBuilder<>(NamedBlockPositionNode.class)
            .addProperties("name", BasicPropertySchemas.OPTIONAL_STRING)
            .addProperties("position", BLOCK_POS)
            .build();

    public static final Property<String> NAME = SCHEMA.getProperty("name", String.class)
            .orElseThrow();

    private final SimplePropertyDataStore<NamedBlockPositionNode> dataStore;

    public NamedBlockPositionNode(SimplePropertyDataStore<NamedBlockPositionNode> dataStore) {
        this.dataStore = dataStore;
    }

    public static NamedBlockPositionNode create() {
        return new NamedBlockPositionNode(new SimplePropertyDataStore(SCHEMA));
    }

    @Override
    public NodeDataSchema<NamedBlockPositionNode> getSchema() {
        return SCHEMA;
    }

    @Override
    public NodeDataAccess<NamedBlockPositionNode> dataAccess() {
        return dataStore.makeAccess();
    }

    @Override
    public NodeConnectionPoint connector(Property<?> property) {
        return null;
    }
}
