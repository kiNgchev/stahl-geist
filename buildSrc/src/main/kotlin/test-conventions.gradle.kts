plugins {
    id("kotlin-conventions")
}

val libs = the<VersionCatalogsExtension>().named("libs")

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    testImplementation(libs.findBundle("spring-test-implementation").get())
    testRuntimeOnly(libs.findBundle("spring-test-runtime-only").get())
}

// Configure the test task
tasks.withType(Test::class.java) {
    outputs.dir(project.extra["snippetsDir"]!!)
}
