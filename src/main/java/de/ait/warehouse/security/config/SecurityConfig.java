package de.ait.warehouse.security.config;

import de.ait.warehouse.security.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, TokenFilter tokenFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        // открыто всем
                        .requestMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()

                        // справочники — только ADMIN
                        .requestMatchers(HttpMethod.POST, "/items", "/categories", "/suppliers", "/locations").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/items/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/items/**").hasRole("ADMIN")

                        // движение товара — USER и ADMIN
                        .requestMatchers(HttpMethod.POST, "/deliveries", "/sales").hasAnyRole("USER", "ADMIN")

                        // всё остальное (все GET) — любой залогиненный
                        .anyRequest().authenticated()


                )
                .exceptionHandling(x -> x.authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


}
