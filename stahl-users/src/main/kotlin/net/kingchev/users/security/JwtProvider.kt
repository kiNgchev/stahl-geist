package net.kingchev.users.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import net.kingchev.users.config.JwtProperties
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
public class JwtProvider(private val props: JwtProperties) {
    private fun extractClaims(bearerToken: String): Jws<Claims> {
        val hmacKey = decodeKey(props.secret)
        return Jwts.parser()
            .verifyWith(hmacKey)
            .build()
            .parseSignedClaims(bearerToken)
    }

    public fun <T> extractClaimBody(bearerToken: String, claimsResolver: (Claims) -> T): T {
        val jwsClaims = extractClaims(bearerToken)
        return claimsResolver(jwsClaims.payload)
    }

    public fun <T> extractClaimHeader(bearerToken: String, claimsResolver: (JwsHeader) -> T): T {
        val jwsClaims = extractClaims(bearerToken)
        return claimsResolver(jwsClaims.header)
    }

    public fun extractExpiry(bearerToken: String): Date = extractClaimBody(bearerToken) {
        obj: Claims -> obj.expiration
    }

    public fun extractUsername(bearerToken: String): String = extractClaimBody(bearerToken) {
        obj: Claims -> obj.subject
    }

    private fun decodeKey(encodedKey: String): SecretKeySpec = SecretKeySpec(
        Base64.getDecoder().decode(encodedKey),
        "HmacSHA256"
    )

    private fun isTokenExpired(bearerToken: String): Boolean {
        return extractExpiry(bearerToken).before(Date())
    }

    public fun createToken(claims: Map<String, String?>, subject: String): String {
        val expiration = Date(System.currentTimeMillis() + props.accessTokenExpiration.toMillis())
        val hmacKey = decodeKey(props.secret)
        return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expiration)
            .signWith(hmacKey)
            .compact()
    }

    public fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }
}