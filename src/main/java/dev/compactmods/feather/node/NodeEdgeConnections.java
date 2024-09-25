package dev.compactmods.feather.node;

import dev.compactmods.feather.api.node.NodePropertySet;
import dev.compactmods.feather.api.property.Property;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NodeEdgeConnections<NodeKey> {

    private final NodePropertySet schema;
    private final Set<Property<?>> connectableProperties;
    private final Map<Property<?>, PropertyConnectionType> connectionTypes;

    public NodeEdgeConnections(NodePropertySet schema) {
        this.schema = schema;
        this.connectableProperties = new HashSet<>();
        this.connectionTypes = new HashMap<>();
        this.generateConnectionPoints();
    }

    private void generateConnectionPoints() {
        this.connectableProperties.clear();
        schema.properties().forEach(prop -> connectionTypes.put(prop, PropertyConnectionType.NONE));
    }

    public <P> void setConnectionType(Property<P> property, PropertyConnectionType connectionType) {
        var oldType = connectionTypes.put(property, connectionType);
        if(oldType != null) {
            if (connectionType == PropertyConnectionType.NONE) {
                this.connectableProperties.remove(property);
            } else {
                this.connectableProperties.add(property);
            }
        }
    }

    public int count() {
        return connectableProperties.size();
    }
}
