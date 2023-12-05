package com.helios.commerce.config;

import com.helios.commerce.model.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class UserConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( author ->
                        author.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/login","/do-register", "/home/**", "/login/**", "/register", "/shome/**","/search","/menu").permitAll()
                                .requestMatchers("/admin/**").hasAuthority(String.valueOf(Role.ROLE_ADMIN))
                                .requestMatchers("/").hasAuthority("customer")
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login.loginPage("/login")
                                .loginProcessingUrl("/do-login")
                                .defaultSuccessUrl("/", true)
                                .successHandler((request, response, authentication) -> {
                                    if (authentication.getName().equals("admin")) {
                                        response.sendRedirect("/admin");
                                    } else {
                                        response.sendRedirect("/");
                                    }
                                })
                                .permitAll()
                )
                .logout(logout ->
                        logout.invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/")
                                .permitAll()
                )
                .authenticationManager(authenticationManager)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
        ;
        return http.build();
    }
}
