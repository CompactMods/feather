package dev.compactmods.feather.tests;

import dev.compactmods.feather.edge.NodeSystemEdgeManager;
import dev.compactmods.feather.node.NodeDataConnections;
import dev.compactmods.feather.node.DataNodeSchemaBuilder;
import dev.compactmods.feather.node.PropertyConnectionType;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.property.NamedProperty;
import dev.compactmods.feather.tests.example.StringNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EdgeManagerTests {

    @Test
    public void canCreateEdgeManager() {
        NodeSystemEdgeManager manager = new NodeSystemEdgeManager();
        Assertions.assertNotNull(manager);
    }

    @Test
    public void canRegisterNodeInstance() {
        NodeSystemEdgeManager manager = new NodeSystemEdgeManager();

        var NAME = new NamedProperty<>("name", BasicPropertySchemas.STRING);
        var schema = new DataNodeSchemaBuilder<>(StringNode.class)
                .addProperties(NAME)
                .build();

        NodeDataConnections<?> connections = manager.register(UUID.randomUUID(), schema);

        Assertions.assertNotNull(connections);
        // Assertions.assertEquals(0, connections.count());
    }

    @Test
    public void canRegisterNodeConnection() {
        NodeSystemEdgeManager manager = new NodeSystemEdgeManager();

        var NAME = new NamedProperty<>("name", BasicPropertySchemas.STRING);
        var NAME2 = new NamedProperty<>("name2", BasicPropertySchemas.STRING);
        var NAME3 = new NamedProperty<>("name3", BasicPropertySchemas.STRING);
        var schema = new DataNodeSchemaBuilder<>(StringNode.class)
                .addProperties(NAME, NAME2, NAME3)
                .build();

        NodeDataConnections<?> connections = manager.register(UUID.randomUUID(), schema);

        // Specify that NAME is stored data and an output
        connections.setConnectionType(NAME, PropertyConnectionType.OUTPUT_FROM_STORAGE);

        // Specify that NAME2 uses an identity function (data passes through to outputs unchanged)
        connections.setConnectionType(NAME2, PropertyConnectionType.PASS_THROUGH);

        Assertions.assertEquals(2, connections.count());
    }
}
