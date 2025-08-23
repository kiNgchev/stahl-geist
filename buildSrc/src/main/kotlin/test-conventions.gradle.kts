plugins {
    id("kotlin-conventions")
    io.gatling.gradle
}

val libs = the<VersionCatalogsExtension>().named("libs")

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    testImplementation(libs.findBundle("spring-test-implementation").get())
    testRuntimeOnly(libs.findBundle("spring-test-runtime-only").get())

    gatling(libs.findLibrary("datafaker").get())
    gatling(libs.findLibrary("gatling-charts-highcharts").get())
    gatling(libs.findLibrary("gatling-app").get())
    gatling(libs.findLibrary("gatling-recorder").get())
    libs.findBundle("kotlinx-serialization").map {
        gatling(it)
    }
}

// Configure the test task
tasks.withType<Test> {
    outputs.dir(project.extra["snippetsDir"]!!)
    useJUnitPlatform()
}

gatling {
    includeMainOutput = false
    includeTestOutput = false
}