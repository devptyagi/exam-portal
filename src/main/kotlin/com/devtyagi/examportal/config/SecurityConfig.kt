package com.devtyagi.examportal.config

import com.devtyagi.examportal.auth.FilterChainExceptionHandler
import com.devtyagi.examportal.auth.JwtRequestFilter
import com.devtyagi.examportal.auth.JwtUserDetailsService
import com.devtyagi.examportal.enums.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: JwtUserDetailsService,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtRequestFilter: JwtRequestFilter,
    private val filterChainExceptionHandler: FilterChainExceptionHandler
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
            ?.passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http?.authorizeRequests()
            ?.antMatchers("**/signup/**")?.permitAll()
            ?.antMatchers("**/admin/**")?.hasRole(Role.ADMIN.toString())

        http?.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        http?.addFilterBefore(filterChainExceptionHandler, LogoutFilter::class.java)
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}