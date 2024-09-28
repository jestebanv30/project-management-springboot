package com.juanesdev.project_management.config;

import com.juanesdev.project_management.domain.enums.Role;
import com.juanesdev.project_management.exception.AccessDeniedHandlerException;
import com.juanesdev.project_management.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/auth/**").permitAll()
                                // routes = api/course
                                .requestMatchers(HttpMethod.GET, "/course/**").hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.POST, "/course").hasAnyRole(Role.ADMIN.toString())
                                .requestMatchers(HttpMethod.PUT, "/course").hasAnyRole(Role.ADMIN.toString())
                                .requestMatchers(HttpMethod.DELETE, "/course").hasAnyRole(Role.ADMIN.toString())
                                // routes = api/projects
                                .requestMatchers(HttpMethod.GET, "/project/**").hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.POST, "/project").hasAnyRole(Role.ADMIN.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.PUT, "/project").hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.DELETE, "/project").hasAnyRole(Role.ADMIN.toString(), Role.STUDENT.toString())
                                // routes = api/user
                                .requestMatchers(HttpMethod.GET, "/user/**").hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.POST, "/user").hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.PUT, "/user").hasAnyRole(Role.ADMIN.toString(), Role.TEACHER.toString(), Role.STUDENT.toString())
                                .requestMatchers(HttpMethod.DELETE, "/user").hasAnyRole(Role.ADMIN.toString())
                                .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
        configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
