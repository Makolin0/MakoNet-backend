package MakoNetbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize ->
                        authorize
//                                .requestMatchers("/auth/**", "/demo/everyone").permitAll()
//                                .requestMatchers("/swagger-ui/**", "/api/**", "/v3/api-docs/**").permitAll()
//                                .requestMatchers("/h2-console/**").permitAll()
//                                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
//                                .anyRequest().authenticated()
                                .requestMatchers("/test/auth").authenticated()
                                .requestMatchers("/user/**").authenticated()
                                .anyRequest().permitAll()
                )
                .sessionManagement(
                        manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .rememberMe(remember -> remember
                        .tokenValiditySeconds(86400)
                        .key("jakisTekst")
                        .rememberMeParameter("remember"))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable);


        return http.build();
    }




}
