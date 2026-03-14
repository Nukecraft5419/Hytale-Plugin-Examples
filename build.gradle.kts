plugins {
    id("java")
}

group = providers.gradleProperty("group").get()
version = providers.gradleProperty("version").get()

repositories {
    mavenCentral()
    flatDir {
        dirs("libraries")
    }
}

dependencies {
    // Libraries
    compileOnly(files("libraries/HytaleServer.jar"))

    // Tests
    testImplementation(platform(libs.junitBom))
    testImplementation(libs.junitJupiter)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks {
    test {
        useJUnitPlatform()
    }
}

tasks.withType<ProcessResources>().configureEach {
    val props = mapOf(
        "Group" to providers.gradleProperty("group").get(),
        "Name" to providers.gradleProperty("name").get(),
        "Version" to providers.gradleProperty("version").get(),
        "Description" to providers.gradleProperty("description").get(),
        "Website" to providers.gradleProperty("website").get(),
        "Main" to providers.gradleProperty("main").get(),
    )
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("manifest.json") {
        expand(props)
    }
}