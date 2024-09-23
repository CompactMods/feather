package dev.compactmods.feather.property;

import dev.compactmods.feather.api.node.NodeDataAccess;
import dev.compactmods.feather.api.node.NodeDataSchema;
import dev.compactmods.feather.api.property.Property;
import dev.compactmods.feather.api.property.PropertyDataStore;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SimplePropertyDataStore<T> implements PropertyDataStore<T> {
    private final NodeDataSchema<T> schema;
    private final Map<Property<?>, Object> values;

    public SimplePropertyDataStore(NodeDataSchema<T> schema) {
        this.schema = schema;
        this.values = new Reference2ObjectOpenHashMap<>();
    }

    @Override
    public NodeDataAccess<T> makeAccess() {
        var storage = this;
        return new NodeDataAccess<>() {
            @Override
            public NodeDataSchema<T> schema() {
                return storage.schema;
            }

            @Override
            public PropertyDataStore<T> storage() {
                return storage;
            }
        };
    }

    @Override
    public <P> boolean valueMatches(Property<P> property, P value) {
        if(!schema.hasProperty(property))
            return false;

        P current = getUnsafe(property);
        return Objects.equals(current, value);
    }

    @Override
    public <P> Optional<P> get(Property<P> property) {
        if (!schema.hasProperty(property) || !values.containsKey(property)) {
            return Optional.empty();
        }

        P unsafe = getUnsafe(property);
        return Optional.ofNullable(unsafe);
    }

    private <P> P getUnsafe(Property<P> property) {
        var cv = values.get(property);
        return property.schema().dataType().cast(cv);
    }

    @Override
    public <P> void set(Property<P> property, P value) {
        if (!schema.hasProperty(property))
            return;

        values.put(property, value);
    }

    @Override
    public <TNode> Optional<PropertyDataStore<TNode>> tryCast(NodeDataSchema<TNode> schema) {
        if (!schema.nodeType().equals(this.schema.nodeType()))
            return Optional.empty();

        try {
            //noinspection unchecked
            var casted = (PropertyDataStore<TNode>) this;
            return Optional.of(casted);
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }
}
