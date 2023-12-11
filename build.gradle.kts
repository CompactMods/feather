import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("java")
    id("maven-publish")
}

val versionMain: String = System.getenv("VERSION") ?: "0.0.0"

base {
    group = "dev.compactmods.feather"
    version = versionMain
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("com.google.guava:guava:31.0.1-jre")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        val now = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
        attributes(mapOf(
                "Specification-Title" to "Feather",
                "Specification-Version" to "1", // We are version 1 of ourselves
                "Implementation-Title" to "Feather",
                "Implementation-Timestamp" to now
        ))
    }
}

val PACKAGES_URL = System.getenv("GH_PKG_URL") ?: "https://maven.pkg.github.com/compactmods/feather"
publishing {
    publications.register<MavenPublication>("main") {
        from(components.getByName("java"))
    }

    repositories {
        // GitHub Packages
        maven(PACKAGES_URL) {
            name = "GitHubPackages"
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}