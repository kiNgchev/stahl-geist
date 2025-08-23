package net.kingchev.build

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.exclude
import org.gradle.kotlin.dsl.the
import kotlin.toString


fun Provider<MinimalExternalModuleDependency>.module(): String {
    return this.get().module.toString()
}

fun Project.security(): List<String> {
    val libs = the<VersionCatalogsExtension>().named("libs")

    return listOf(
        libs.findLibrary("spring-boot-starter-oauth2-client").get().module(),
        libs.findLibrary("spring-boot-starter-security").get().module()
    )
}

fun Project.data(): List<String> {
    val libs = the<VersionCatalogsExtension>().named("libs")

    return listOf(
        libs.findLibrary("liquibase-core").get().module(),
        libs.findLibrary("postgresql").get().module(),
        libs.findLibrary("spring-boot-starter-data-redis-reactive").get().module(),
        libs.findLibrary("spring-boot-starter-data-jpa").get().module()
    )
}

fun <T : ModuleDependency> T.exclude(dependencies: List<String>): T {
    dependencies.forEach { dependency -> exclude(dependency) }
    return this
}