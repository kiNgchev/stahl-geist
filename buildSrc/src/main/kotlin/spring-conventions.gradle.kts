import org.gradle.kotlin.dsl.the
import org.gradle.api.artifacts.VersionCatalogsExtension

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

dependencies {
    // Implementation dependencies
    implementation(libs.findBundle("spring-implementation").get())

    // CompileOnly dependencies
    compileOnly(libs.findBundle("spring-compile-only").get())

    // DevelopmentOnly dependencies
    developmentOnly(libs.findBundle("spring-development-only").get())

    // RuntimeOnly dependencies
    runtimeOnly(libs.findBundle("spring-runtime-only").get())

    // AnnotationProcessor dependencies
    annotationProcessor(libs.findBundle("spring-annotation-processor").get())
}

dependencyManagement {
    imports {
        mavenBom("de.codecentric:spring-boot-admin-dependencies:${libs.findVersion("spring-boot-admin").get()}")
        mavenBom("org.springframework.modulith:spring-modulith-bom:${libs.findVersion("spring-modulith").get()}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.findVersion("spring-cloud").get()}")
    }
}
