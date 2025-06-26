plugins {
    org.jetbrains.kotlin.jvm
    org.jetbrains.kotlin.plugins.`kotlinx-serialization`
    org.jetbrains.kotlin.plugins.`kotlin-allopen`
}

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
    explicitApi()

    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}