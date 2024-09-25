package dev.compactmods.feather.tests;

import dev.compactmods.feather.NodeSystem;
import dev.compactmods.feather.api.feature.BasicNodeFeatures;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.property.NamedProperty;
import dev.compactmods.feather.node.NodePropertySetBuilder;
import dev.compactmods.feather.property.SimplePropertyDataStore;
import dev.compactmods.feather.property.SimplePropertySchema;
import dev.compactmods.feather.tests.example.NamedBlockPositionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class NodeSchemaTests {

    @Test
    public void canCreateSchemaWithBuilder() {

//        CustomizedMachineGraph cmg;
//        GraphSchema<CustomizedMachineGraph> myGraphSchema = null;
//
//        IGraphSerializer<Codec<CustomizedMachineGraph>> CODEC_SERIALIZER = GraphCodecSerializer.serializer(myGraphSchema, CustomizedMachineGraph.CODEC);
//        IGraphDeserializer<Codec<CustomizedMachineGraph>> CODEC_SERIALIZER = GraphCodecSerializer.deserializer(myGraphSchema, CustomizedMachineGraph.CODEC);

        final var SCHEMA_BLOCKPOS_PROP = SimplePropertySchema.required(0L);

        final var DATA_SCHEMA = new NodePropertySetBuilder()
                .addProperties("position", SCHEMA_BLOCKPOS_PROP)
                .build();

        Assertions.assertNotNull(NamedBlockPositionNode.SCHEMA);
    }

    @Test
    public void canCreateNode() {
        var namedPositionNode = new NamedBlockPositionNode();
//        namedPositionNode.dataAccess()
//                .storage()
//                .set(NamedBlockPositionNode.NAME, "This was changed!");
    }

    @Test
    public void canCreateConnectionHandlerForProperty() {

        var graph = new NodeSystem<>(UUID::randomUUID);

        var NAME = new NamedProperty<>("name", BasicPropertySchemas.STRING);

        var stringNodeDataSchema = new NodePropertySetBuilder()
                .addProperties(NAME)
                .build();

        var stringNodeSchema = graph.addSchema(builder -> builder
                .registerFeature(BasicNodeFeatures.DATA_HOST, (nodeID, schema) -> new SimplePropertyDataStore(stringNodeDataSchema)));

        var node = graph.addNode(stringNodeSchema);
        var node2 = graph.addNode(stringNodeSchema);

//        Assertions.assertEquals(0, node.connections(NAME).count());
//        Assertions.assertEquals(0, node2.connections(NAME).count());
//
//        var out = node.connector(NAME);
//        var in = node2.connector(NAME);

//        graph.connect(out, in);
    }

}
