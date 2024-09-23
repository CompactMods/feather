package dev.compactmods.feather.node;

import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.api.property.Property;
import dev.compactmods.feather.api.property.PropertySchema;
import dev.compactmods.feather.property.NamedProperty;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataNodeSchemaBuilder<S> {

    protected final Class<S> nodeType;
    protected final Set<Property<?>> properties;
    protected final Map<String, PropertySchema<?>> additionalPropertySchemas;

    public DataNodeSchemaBuilder(Class<S> nodeType) {
        this.nodeType = nodeType;
        this.properties = new HashSet<>();
        this.additionalPropertySchemas = new HashMap<>();
    }

    public DataNodeSchemaBuilder<S> addProperties(Property<?>... properties) {
        this.properties.addAll(Arrays.asList(properties));
        return this;
    }

    public <DataType> DataNodeSchemaBuilder<S> addProperties(String name, PropertySchema<DataType> propSchema) {
        additionalPropertySchemas.put(name, propSchema);
        return this;
    }

    public NodeDataSchema<S> build() {
        Map<String, Property<?>> realProperties = buildProperties();
        return new SimpleNodeDataSchema<S>(nodeType, realProperties);
    }

    protected Map<String, Property<?>> buildProperties() {
        var map = new Object2ObjectArrayMap<String, Property<?>>();
        properties.forEach(prop -> map.put(prop.name(), prop));
        additionalPropertySchemas.forEach((name, schema) -> {
            map.put(name, new NamedProperty<>(name, schema));
        });
        return map;
    }
}
