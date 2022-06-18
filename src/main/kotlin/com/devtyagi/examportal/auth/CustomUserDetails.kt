package com.devtyagi.examportal.auth

import com.devtyagi.examportal.dao.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val user: User,
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val simpleGrantedAuthority = SimpleGrantedAuthority("ROLE_" + user.role.toString())
        return mutableListOf(simpleGrantedAuthority)
    }

    fun getUser(): User {
        return user
    }

    fun getName(): String {
        return user.name
    }

    fun getUserId(): String? {
        return user.userId
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}