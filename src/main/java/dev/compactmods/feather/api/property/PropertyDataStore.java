package dev.compactmods.feather.api.property;

import java.util.Optional;

public interface PropertyDataStore {

    <P> boolean valueMatches(Property<P> property, P value);

    <P> Optional<P> get(Property<P> property);
    <P> void set(Property<P> property, P value);
}
