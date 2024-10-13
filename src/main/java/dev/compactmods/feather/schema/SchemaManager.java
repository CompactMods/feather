package dev.compactmods.feather.schema;

import dev.compactmods.feather.api.schema.Schema;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;

import java.util.List;

public class SchemaManager<T> {

    protected final List<Schema<T>> schemas;

    public SchemaManager() {
        this.schemas = new ReferenceArrayList<>();
    }
}
