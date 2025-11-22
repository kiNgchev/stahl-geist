plugins {
    id("kotlin-conventions")
    id("spring-conventions")
}

dependencies {
    implementation(libs.spring.boot.starter.liquibase)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)
}

tasks.bootJar {
    enabled = false
}