package com.devtyagi.examportal.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtRequestFilter(
    private val jwtUserDetailsService: JwtUserDetailsService,
    private val jwtUtil: JwtUtil
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        var email: String? = null
        var jwtToken: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7)
            email = jwtUtil.extractUsername(jwtToken)
        }

        if (email != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: CustomUserDetails = jwtUserDetailsService.loadUserByUsername(email) as CustomUserDetails
            if (jwtUtil.validateToken(jwtToken, userDetails)!!) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }

        filterChain.doFilter(request, response)
    }

}