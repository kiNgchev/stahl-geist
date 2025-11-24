plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    id("test-conventions")
    id("metrics-conventions")
}

dependencies {
    implementation(project(":stahl-shared"))
    implementation(project(":stahl-models"))
}
