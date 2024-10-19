package dev.compactmods.feather.tests.junit;

import dev.compactmods.feather.edge.NodeSystemEdgeManager;
import dev.compactmods.feather.node.NodeConnections;
import dev.compactmods.feather.node.NodePropertySetBuilder;
import dev.compactmods.feather.node.PropertyConnectionType;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.property.NamedProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EdgeManagerTests {

    @Test
    public void canCreateEdgeManager() {
        NodeSystemEdgeManager<UUID> manager = new NodeSystemEdgeManager<>();
        Assertions.assertNotNull(manager);
    }

    @Test
    public void canRegisterNodeInstance() {
        NodeSystemEdgeManager<UUID> manager = new NodeSystemEdgeManager<>();

        var NAME = new NamedProperty<>("name", BasicPropertySchemas.STRING);
        var schema = new NodePropertySetBuilder()
                .addProperties(NAME)
                .build();

        NodeConnections<UUID> connections = manager.register(UUID.randomUUID(), schema);

        Assertions.assertNotNull(connections);
        // Assertions.assertEquals(0, connections.count());
    }

    @Test
    public void canRegisterNodeConnection() {
        NodeSystemEdgeManager<UUID> manager = new NodeSystemEdgeManager<>();

        var NAME = new NamedProperty<>("name", BasicPropertySchemas.STRING);
        var NAME2 = new NamedProperty<>("name2", BasicPropertySchemas.STRING);
        var NAME3 = new NamedProperty<>("name3", BasicPropertySchemas.STRING);
        var schema = new NodePropertySetBuilder()
                .addProperties(NAME, NAME2, NAME3)
                .build();

        NodeConnections<UUID> connections = manager.register(UUID.randomUUID(), schema);

        // Specify that NAME is stored data and an output
        connections.setConnectionType(NAME, PropertyConnectionType.OUTPUT_FROM_STORAGE);

        // Specify that NAME2 uses an identity function (data passes through to outputs unchanged)
        connections.setConnectionType(NAME2, PropertyConnectionType.PASS_THROUGH);

        Assertions.assertEquals(2, connections.count());
    }
}
