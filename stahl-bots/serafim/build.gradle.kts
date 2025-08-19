plugins {
    id("kotlin-conventions")
    id("spring-conventions")
}

dependencies {
    implementation("com.github.twitch4j:twitch4j:1.25.0")
    implementation(project(":stahl-shared"))
}