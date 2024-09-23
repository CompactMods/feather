package dev.compactmods.feather.tests;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.property.NamedProperty;
import dev.compactmods.feather.node.DataNodeSchemaBuilder;
import dev.compactmods.feather.property.SimplePropertySchema;
import dev.compactmods.feather.tests.example.NamedBlockPositionNode;
import dev.compactmods.feather.tests.example.StringNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NodeSchemaTests {

    @Test
    public void canCreateSchemaWithBuilder() {

//        CustomizedMachineGraph cmg;
//        GraphSchema<CustomizedMachineGraph> myGraphSchema = null;
//
//        IGraphSerializer<Codec<CustomizedMachineGraph>> CODEC_SERIALIZER = GraphCodecSerializer.serializer(myGraphSchema, CustomizedMachineGraph.CODEC);
//        IGraphDeserializer<Codec<CustomizedMachineGraph>> CODEC_SERIALIZER = GraphCodecSerializer.deserializer(myGraphSchema, CustomizedMachineGraph.CODEC);

        final var SCHEMA_BLOCKPOS_PROP = SimplePropertySchema.required(0L);

        final var SCHEMA = new DataNodeSchemaBuilder<>(NodeDataAccess.class)
                .addProperties("position", SCHEMA_BLOCKPOS_PROP)
                .build();

        Assertions.assertNotNull(NamedBlockPositionNode.SCHEMA);
    }

    @Test
    public void canCreateNode() {
        var namedPositionNode = NamedBlockPositionNode.create();
        namedPositionNode.dataAccess()
                .storage()
                .set(NamedBlockPositionNode.NAME, "This was changed!");
    }

    @Test
    public void canCreateConnectionHandlerForProperty() {

        var graph = new NodeSystem();

        var NAME = new NamedProperty<>("name", BasicPropertySchemas.STRING);

        var schema = new DataNodeSchemaBuilder<>(StringNode.class)
                .addProperties(NAME)
                .build();

        var node = graph.addNode(schema);
        var node2 = graph.addNode(schema);

//        Assertions.assertEquals(0, node.connections(NAME).count());
//        Assertions.assertEquals(0, node2.connections(NAME).count());
//
//        var out = node.connector(NAME);
//        var in = node2.connector(NAME);

//        graph.connect(out, in);
    }

}
