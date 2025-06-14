plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(plugin(libs.plugins.kotlin.jvm))
    implementation(plugin(libs.plugins.kotlin.serialization))
    implementation(plugin(libs.plugins.kotlin.spring))
    implementation(plugin(libs.plugins.kotlin.allopen))
    implementation(plugin(libs.plugins.jmh))
    implementation(plugin(libs.plugins.dependency.managment))
    implementation(plugin(libs.plugins.spring.boot))
    implementation(plugin(libs.plugins.asciidoctor))
}

fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }