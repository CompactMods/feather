package dev.compactmods.feather.node.schema;

import com.google.common.collect.ImmutableMap;
import dev.compactmods.feather.connection.MutableNodeDataSchema;
import dev.compactmods.feather.connection.NodeDataSchema;
import dev.compactmods.feather.node.data.NodeDataStore;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComposedNodeSchema<DataStorage> implements NodeSchema<DataStorage>, NodeDataSchemaProvider {
    private final ImmutableMap<String, NodeDataSchema<?>> properties;
    private final Set<String> inputSchemas;
    private final Set<String> outputSchemas;

    public ComposedNodeSchema(Map<String, NodeDataSchema<?>> inputs) {
        this.properties = ImmutableMap.copyOf(inputs);
        this.inputSchemas = inputs.entrySet().stream()
                .filter(a -> a.getValue().isInput())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        this.outputSchemas = inputs.entrySet().stream()
                .filter((a) -> a.getValue().isOutput())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Stream<String> inputNames() {
        return inputSchemas.stream();
    }

    @Override
    public Stream<String> outputNames() {
        return outputSchemas.stream();
    }

    @Override
    public Optional<NodeDataSchema<?>> schema(String name) {
        if(!properties.containsKey(name)) return Optional.empty();
        var schema = properties.get(name);
        return Optional.ofNullable((NodeDataSchema<?>) schema);
    }

    @Override
    public int connectorCount() {
        return properties.size() + outputSchemas.size();
    }
}
