plugins {
    id("kotlin-conventions")
    org.asciidoctor.jvm.convert
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}