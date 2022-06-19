package com.devtyagi.examportal.config

import com.devtyagi.examportal.auth.FilterChainExceptionHandler
import com.devtyagi.examportal.auth.JwtRequestFilter
import com.devtyagi.examportal.auth.JwtUserDetailsService
import com.devtyagi.examportal.constants.Endpoints
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
import org.springframework.web.cors.CorsUtils

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
                ?.requestMatchers(CorsUtils::isPreFlightRequest)?.permitAll()
            ?.antMatchers(Endpoints.BASE_URL + "/**/login")?.permitAll()
            ?.antMatchers(Endpoints.BASE_URL + "/admin/get-subjects")?.authenticated()
            ?.antMatchers(Endpoints.BASE_URL + "/admin/**")?.hasRole(Role.ADMIN.toString())
            ?.antMatchers(Endpoints.BASE_URL + "/teacher/**")?.hasRole(Role.TEACHER.toString())
            ?.antMatchers(Endpoints.BASE_URL + "/student/**")?.hasRole(Role.STUDENT.toString())

        http?.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        http?.addFilterBefore(filterChainExceptionHandler, LogoutFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}