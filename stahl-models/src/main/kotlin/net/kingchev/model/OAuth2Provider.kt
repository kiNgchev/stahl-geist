package net.kingchev.model

public val DEFAULT_PROVIDER: OAuth2Provider = OAuth2Provider.LOCAL

public enum class OAuth2Provider {
    DISCORD,
    TELEGRAM,
    LOCAL
}

public fun getProvider(provider: String): OAuth2Provider =
    OAuth2Provider.entries.find { it.name.equals(provider, true) }
        ?: DEFAULT_PROVIDER