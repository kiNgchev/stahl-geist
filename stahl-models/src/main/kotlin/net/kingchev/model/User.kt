package net.kingchev.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

public class User(
    private var password: String,
    private var username: String,
    private var authorities: MutableList<GrantedAuthority>,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = this.authorities

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username
}