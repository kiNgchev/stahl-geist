plugins {
    id("kotlin-conventions")
    id("spring-bom-conventions")
}

dependencies {
    implementation(libs.spring.boot.starter.cache)
    implementation(libs.caffeine)
    implementation(libs.bundles.logback.implementation)
    implementation(libs.spring.cloud.starter.netflix.eureka.client)
    implementation(libs.spring.cloud.CONFIG_SERVER_URL)
    implementation(libs.spring.boot.starter.security)
}