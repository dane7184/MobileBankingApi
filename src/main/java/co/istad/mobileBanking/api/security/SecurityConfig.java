package co.istad.mobileBanking.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //TODO : Define in memory user
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}123")
                .roles("ADMIN")
                .build();
        UserDetails goldUser = User.builder()
                .username("gold")
                .password("{noop}123")
                .roles("ACCOUNT")
                .build();

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(goldUser);
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //
        http.csrf(csrf -> csrf.disable());

        //Authorize URL mapping
        http.authorizeHttpRequests(request ->{
            request.requestMatchers("/api/v1/users/**").hasRole("ADMIN");
            request.requestMatchers("/api/v1/account-type/**", "/api/v1/files/**").hasAnyRole("ADMIN", "ACCOUNT");
            request.anyRequest().permitAll();
                });

        // Security mechanism
        http.httpBasic();

        // MAKE security
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
