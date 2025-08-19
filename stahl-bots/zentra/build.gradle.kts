plugins {
    id("kotlin-conventions")
    id("spring-conventions")
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
    id("eu.vendeli.telegram-bot") version "8.1.0"
}

dependencies {
    implementation(project(":stahl-shared"))
}

ktGram {
    aideEnabled = false
}