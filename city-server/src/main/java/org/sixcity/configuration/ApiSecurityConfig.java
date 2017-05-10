package org.sixcity.configuration;

import org.sixcity.security.ApiAuthenticationTokenFilter;
import org.sixcity.security.JwtAuthenticationEntryPoint;
import org.sixcity.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Order(1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private ApiAuthenticationTokenFilter apiAuthenticationTokenFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    /*@Bean
    public ApiAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new ApiAuthenticationTokenFilter();
    }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //later turn on
                .csrf().disable()

                .antMatcher("/api/**")

                .authorizeRequests().antMatchers("/api/rebate/getAppToken").permitAll()

                .anyRequest().authenticated().and()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                .httpBasic();

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(apiAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
