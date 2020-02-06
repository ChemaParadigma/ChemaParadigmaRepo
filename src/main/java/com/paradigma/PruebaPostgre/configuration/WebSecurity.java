package com.paradigma.PruebaPostgre.configuration;

import com.paradigma.PruebaPostgre.service.impl.UserServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceLogin userServiceLogin;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceLogin);
    }

    @Override
    public void configure(HttpSecurity web) throws Exception {
        web.csrf().disable().authorizeRequests().antMatchers("/login").permitAll() //Permitimos "/login" a cualquiera
                .anyRequest().authenticated() //cualquier otra requiere autenticación
                .and()

                //Las peticiones login pasaran por este filtro
                .addFilterBefore(new LoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                //Las demas peticiones pasaran por el filtro de valdiar token
                .addFilterBefore(new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
