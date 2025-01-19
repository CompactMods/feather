package dev.compactmods.feather.core.feature;

import dev.compactmods.feather.core.schema.Schema;
import dev.compactmods.feather.core.schema.SchemaHolder;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import org.jetbrains.annotations.Nullable;

public class FeatureManager<TParent extends SchemaHolder<TParent> & FeatureHolder<TParent>> {

    private record FeatureIdentifier<T>(String name, Feature<T> type) {}

    @org.jetbrains.annotations.NotNull
    private final TParent parent;
    private Object2ObjectMap<FeatureIdentifier<?>, Object> featureInstances;

    public FeatureManager(TParent parent) {
        this.parent = parent;
        this.featureInstances = new Object2ObjectArrayMap<>();

        createAndRegisterFeatures(parent.schema());
    }

    public void createAndRegisterFeatures(Schema<TParent> schema) {
        final var featManager = schema.features();
        var initializer = schema.featureInitializer(feat);
        var instance = initializer.createInstance(schema);

        this.featureInstances.put(feat, instance);
    }

    public <TFeatClass> TFeatClass getFeature(Feature<TFeatClass> feature) {
        return getFeature(feature, "default");
    }

    @Nullable
    public <TFeatClass> TFeatClass getFeature(Feature<TFeatClass> feature, String name) {
        var key = new FeatureIdentifier<>(name, feature);
        final var inst = featureInstances.get(key);

        if (inst == null) return null;
        if (feature.featureClass().isInstance(inst)) {
            return feature.featureClass().cast(inst);
        }

        return null;
    }
}
