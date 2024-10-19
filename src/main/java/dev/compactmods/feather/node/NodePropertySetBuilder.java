package dev.compactmods.feather.node;

import dev.compactmods.feather.core.node.NodePropertySet;
import dev.compactmods.feather.core.node.property.Property;
import dev.compactmods.feather.core.node.property.PropertySchema;
import dev.compactmods.feather.property.NamedProperty;
import dev.compactmods.feather.property.SimpleNodeDataSchema;
import it.unimi.dsi.fastutil.objects.ReferenceImmutableList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NodePropertySetBuilder {

    protected final Set<Property<?>> properties;
    protected final Map<String, PropertySchema<?>> additionalPropertySchemas;

    public NodePropertySetBuilder() {
        this.properties = new HashSet<>();
        this.additionalPropertySchemas = new HashMap<>();
    }

    public NodePropertySetBuilder addProperties(Property<?>... properties) {
        this.properties.addAll(Arrays.asList(properties));
        return this;
    }

    public <DataType> NodePropertySetBuilder addProperties(String name, PropertySchema<DataType> propSchema) {
        additionalPropertySchemas.put(name, propSchema);
        return this;
    }

    public NodePropertySet build() {
        var realProperties = buildProperties();
        return new SimpleNodeDataSchema(new ReferenceImmutableList<>(realProperties));
    }

    protected Set<Property<?>> buildProperties() {
        var map = new HashSet<>(properties);
        additionalPropertySchemas.forEach((name, schema) -> {
            map.add(new NamedProperty<>(name, schema));
        });
        return map;
    }
}
