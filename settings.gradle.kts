rootProject.name = "feather"

dependencyResolutionManagement {
    versionCatalogs.create("fastutil") {
        version("fastutil") {
            require("[8,9)")
            prefer("8.5.12")
        }

        library("fastutil", "it.unimi.dsi", "fastutil")
            .versionRef("fastutil")
    }
}