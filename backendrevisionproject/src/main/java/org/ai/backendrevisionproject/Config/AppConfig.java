package org.ai.backendrevisionproject.Config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Autowired
    private JwtFilter jwtFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize->authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .build();

    }

    private CorsConfigurationSource corsConfigurationSource() {
   return new CorsConfigurationSource() {
       @Override
       public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
           CorsConfiguration configuration=new CorsConfiguration();
           configuration.setAllowedOrigins(Arrays.asList(
                   "http://localhost:3000",
                   "http://localhost:5173",
                   "http://localhost:5174",
                   "http://localhost:5175",
                   "http://localhost:4200"
           ));
           configuration.setAllowedMethods(Collections.singletonList("*"));
           configuration.setAllowedHeaders(Collections.singletonList("*"));
           configuration.setExposedHeaders(Collections.singletonList("Authorization"));
           configuration.setAllowCredentials(true);
           configuration.setMaxAge(3600L);

           return  configuration;
       }
   };
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
