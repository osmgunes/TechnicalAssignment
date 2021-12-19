package com.ebi.TechnicalAssignment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/relation/**").hasAuthority(ApplicationUserPermission.SEARCH.getPermission())
                .antMatchers(HttpMethod.GET, "/mouse/**").hasAuthority(ApplicationUserPermission.SEARCH.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails osmanUser = User.builder()
                .username("osman")
                .password(passwordEncoder.encode("pass123"))
                .authorities(ApplicationUserRole.USERS.getGrantedAuthorities())
                .build();
        UserDetails jamesUser = User.builder()
                .username("James")
                .password(passwordEncoder.encode("pass123"))
                .authorities(ApplicationUserRole.USERS.getGrantedAuthorities())
                .build();

        UserDetails tudorUser = User.builder()
                .username("tudor")
                .password(passwordEncoder.encode("pass123"))
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails robertUser = User.builder()
                .username("Robert")
                .password(passwordEncoder.encode("pass123"))
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails csabaUser = User.builder()
                .username("Csaba")
                .password(passwordEncoder.encode("pass123"))
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(
                osmanUser,
                tudorUser,
                robertUser,
                csabaUser,
                jamesUser
        );

    }

}
