package dev.compactmods.feather.tests.example;

import dev.compactmods.feather.api.property.Property;
import dev.compactmods.feather.property.BasicPropertySchemas;
import dev.compactmods.feather.property.NamedProperty;

public final class TestNodeProperties {

    public static final Property<String> OPTIONAL_NAME = new NamedProperty<>("name", BasicPropertySchemas.OPTIONAL_STRING);

    public static final Property<String> OPTIONAL_STRING_VALUE = new NamedProperty<>("value", BasicPropertySchemas.OPTIONAL_STRING);

}
