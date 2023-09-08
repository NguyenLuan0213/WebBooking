/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.configs;

import com.web.handlers.LoginHandler;
import com.web.handlers.LogoutHandler;
import com.web.pojo.Customer;
import com.web.pojo.Staff;
import com.web.service.CustomerService;
import com.web.service.StaffService;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    "com.web.handlers",
    "com.web.components"
})
@PropertySource("classpath:configs.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StaffService staffService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("cusUserDetailsService")
    private UserDetailsService cusUserDetailsService;

    @Autowired
    private Environment env;

    @Autowired
    private LoginHandler loginHandler;
    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if (staffService.isStaffUser(username)) {
                    UserDetails staffUserDetails = staffService.loadUserByUsername(username);
                    return staffUserDetails;
                } else if (customerService.isCustomerUser(username)) {
                    UserDetails customerUserDetails = customerService.loadUserByUsername(username);
                    return customerUserDetails;
                } else {
                    throw new UsernameNotFoundException("User not found: " + username);
                }
            }
        }).passwordEncoder(passwordEncoder());
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("userName")
                .passwordParameter("passWord");

        http.formLogin().successHandler(loginHandler).failureUrl("/login?error");

        http.logout().logoutSuccessHandler(logoutHandler);

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");

        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("STAFF", "ADMIN", "OWNER")
                //                                .antMatchers("/customer/**").hasAnyRole("CUSTOMER");

                .antMatchers("/admin/**").
                hasAuthority(Staff.ADMIN)
                .antMatchers("/customer/**").
                hasAuthority(Customer.CUSTOMER)
                .antMatchers("/staff/**").
                hasAuthority(Staff.STAFF)
                .antMatchers("/owner/**").
                hasAuthority(Staff.OWNER)
                .antMatchers("/driver/**").
                hasAuthority(Staff.ADMIN);
        http.csrf().disable();
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

}
