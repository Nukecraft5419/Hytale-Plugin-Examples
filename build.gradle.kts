plugins {
    id("java")
}

group to project.property("group")
version to project.property("version")

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
        "Group" to project.property("group"),
        "Name" to project.property("name"),
        "Version" to project.property("version"),
        "Description" to project.property("description"),
        "Website" to project.property("website"),
        "Main" to project.property("main"),
    )
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("manifest.json") {
        expand(props)
    }
}