plugins {
    id("kotlin-conventions")
    id("spring-conventions")
}

dependencies {
    api(project(":stahl-shared"))
    api(project(":stahl-models"))
}