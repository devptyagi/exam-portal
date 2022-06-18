package com.devtyagi.examportal.auth

import com.devtyagi.examportal.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService{

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findUserByEmail(email) ?: throw UsernameNotFoundException("No user exists with given email")
        return CustomUserDetails(user)
    }

}