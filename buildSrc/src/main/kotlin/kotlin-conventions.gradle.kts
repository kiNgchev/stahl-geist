plugins {
    org.jetbrains.kotlin.jvm
    org.jetbrains.kotlin.plugin.serialization
    org.jetbrains.kotlin.plugin.allopen
    org.jetbrains.kotlin.kapt
}

repositories {
    mavenCentral()
}

val libs = the<VersionCatalogsExtension>().named("libs")

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    all {
        resolutionStrategy {
            libs.findBundle("kotlinx-serialization").get().map {
                force(it)
            }
        }
    }
}

dependencies {
    implementation(libs.findBundle("kotlinx-serialization").get())
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