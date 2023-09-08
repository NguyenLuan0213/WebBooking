
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.configs;

import com.web.filters.JWTAuthenticationTokenFilter;
import com.web.filters.RestAuthenticationEntryPoint;
import com.web.filters.CustomAccessDeniedHandlers;
import com.web.pojo.Customer;
import com.web.pojo.Staff;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.web.controllers",
    "com.web.repository",
    "com.web.service",
    "com.web.configs",
    "com.web.formatters",
    "com.web.handlers"
})
@PropertySource("classpath:configs.properties")
@Order(1)
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JWTAuthenticationTokenFilter jWTAuthenticationTokenFilter() throws Exception {
        JWTAuthenticationTokenFilter jWTAuthenticationTokenFilter = new JWTAuthenticationTokenFilter();
        jWTAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jWTAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandlers customAccessDeniedHandlers() {
        return new CustomAccessDeniedHandlers();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/api/**"));
        http.cors();
        http.authorizeRequests()
                .antMatchers("/api").permitAll()
                .antMatchers("/api/login/").permitAll()
                .antMatchers("/api/register/").permitAll()
                .antMatchers("/api/admin/**").hasAuthority(Staff.ADMIN)
                .antMatchers("/api/staff/**").hasAuthority(Staff.STAFF)
                .antMatchers("/api/owner/**").hasAuthority(Staff.OWNER)
                .antMatchers("/api/driver/**").hasAuthority(Staff.DRIVER)
                .antMatchers("/api/customer/**").hasAuthority(Customer.CUSTOMER);

        http.antMatcher("/api/**").httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasAuthority(Staff.ADMIN)
                .antMatchers("/api/staff/**").hasAuthority(Staff.STAFF)
                .antMatchers("/api/customer/**").hasAuthority(Customer.CUSTOMER)
                .and()
                .addFilterBefore(jWTAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandlers());
    }

}
