package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.node.Node;
import dev.compactmods.feather.node.schema.NodeSchema;

import java.util.UUID;

public record NamedBlockPositionNode(UUID id, MutableStorage<Data> dataStore) implements Node<NamedBlockPositionNode.Data> {

    private static final NodeSchema<Data> SCHEMA = NodeSchema.<Data>builder()
            .addData("name", "", name -> name.input()
                    .optional()
                    .readable(Data::name)
                    .modifiable(Data::setName))
            .addData("position", 0L, position -> position.input().output()
                    .readable(Data::packedPosition))
            .build();

    public static NamedBlockPositionNode create() {
        return new NamedBlockPositionNode(UUID.randomUUID(), new MutableStorage<>(new Data()));
    }

    @Override
    public NodeSchema<Data> schema() {
        return SCHEMA;
    }

    public static class Data {
        String name;
        long packedPosition;

        public String name() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long packedPosition() { return packedPosition; }
    }
}
