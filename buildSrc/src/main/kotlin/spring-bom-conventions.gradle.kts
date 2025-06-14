plugins {
    id("kotlin-conventions")
    io.spring.`dependency-management`
    org.springframework.boot
    org.jetbrains.kotlin.plugin.spring
}

repositories {
    mavenCentral()
}

val libs = the<VersionCatalogsExtension>().named("libs")

dependencyManagement {
    imports {
        mavenBom("de.codecentric:spring-boot-admin-dependencies:${libs.findVersion("spring-boot-admin").get()}")
        mavenBom("org.springframework.modulith:spring-modulith-bom:${libs.findVersion("spring-modulith").get()}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.findVersion("spring-cloud").get()}")
    }
}
