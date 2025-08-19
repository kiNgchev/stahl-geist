plugins {
    id("kotlin-conventions")
    id("spring-conventions")
}

val kordVersion = "0.15.0"

dependencies {
    implementation("dev.kord:kord-core:${kordVersion}")
    implementation("dev.kord:kord-common:${kordVersion}")
    implementation("dev.kord:kord-rest:${kordVersion}")
    implementation("dev.kord:kord-gateway:${kordVersion}")
    implementation(project(":stahl-shared"))
}