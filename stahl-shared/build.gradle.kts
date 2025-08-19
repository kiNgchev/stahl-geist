plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    id("test-conventions")
    id("metrics-conventions")
}

dependencies {
    // Implementation dependencies
    api(libs.bundles.spring.implementation)
}

tasks.bootJar {
    enabled = false
}