package net.kingchev.build

object ProjectMetadata {
    const val PREFIX = "stahl-"
    const val PACKAGE: String = "net.kingchev"
    val VERSION: String = Version.getVersion()
}

object Version {
    const val MAJOR: String = "1"
    const val MINOR: String = "0"
    const val PATCH: String = "0"
    const val IS_SNAPSHOT = false

    fun getVersion(): String {
        return "$MAJOR.$MINOR.$PATCH" + if (IS_SNAPSHOT) "-SNAPSHOT" else ""
    }
}