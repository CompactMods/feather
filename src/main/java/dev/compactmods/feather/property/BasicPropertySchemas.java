package dev.compactmods.feather.property;

import dev.compactmods.feather.core.node.property.PropertySchema;

public class BasicPropertySchemas {

    public static final PropertySchema<String> STRING = SimplePropertySchema.required("");

    public static final PropertySchema<String> OPTIONAL_STRING = SimplePropertySchema.optional("");
}
