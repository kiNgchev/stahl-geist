plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    id("test-conventions")
}

dependencies {
    implementation(project(":stahl-shared"))
    implementation(project(":stahl-models"))
}
