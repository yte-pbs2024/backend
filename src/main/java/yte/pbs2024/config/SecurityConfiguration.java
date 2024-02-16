package yte.pbs2024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfiguration {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public List<UserDetails> configureGlobal() throws Exception {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user" ))
                .authorities("ROLE_USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin" ))
                .authorities("ADMIN")
                .build();

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(user)
                .withUser(admin);

        return Arrays.asList(user, admin);
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        httpSecurity
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest.requestMatchers("/welcome").hasAuthority("ADMIN").anyRequest().authenticated());
        return httpSecurity.build();
    }


}