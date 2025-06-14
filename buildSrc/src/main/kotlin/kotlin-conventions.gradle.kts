import org.gradle.kotlin.dsl.the
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    org.jetbrains.kotlin.jvm
    org.jetbrains.kotlin.plugins.`kotlinx-serialization`
    org.jetbrains.kotlin.plugins.`kotlin-allopen`
}

val libs = the<VersionCatalogsExtension>().named("libs")

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}