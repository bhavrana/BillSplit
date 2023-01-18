package com.example.billsplit.security.config;

import com.example.billsplit.security.filter.BillSplitJWTAuthenticationFilter;
import com.example.billsplit.security.filter.GmailAuthenticationFilter;
import com.example.billsplit.security.provider.BillSplitJWTAuthenticationProvider;
import com.example.billsplit.security.provider.GmailAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private GmailAuthenticationProvider gmailAuthenticationProvider;

    @Autowired
    private BillSplitJWTAuthenticationProvider billSplitJWTAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(gmailAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(billSplitJWTAuthenticationProvider);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   GmailAuthenticationFilter gmailAuthenticationFilter,
                                                   BillSplitJWTAuthenticationFilter billSplitJWTAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringRequestMatchers(toH2Console())
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .addFilterAt(gmailAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(billSplitJWTAuthenticationFilter, GmailAuthenticationFilter.class);
        return http.build();
    }
}
