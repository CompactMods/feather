package dev.compactmods.feather.core.feature;

import dev.compactmods.feather.core.schema.SchemaHolder;

public interface FeatureHolder<TParent extends SchemaHolder<TParent> & FeatureHolder<TParent>> {
    FeatureManager<TParent> featureManager();
}
