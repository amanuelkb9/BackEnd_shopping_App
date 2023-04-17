package edu.miu.shopmartbackend.config;

import edu.miu.shopmartbackend.authfilters.CustomAuthenticationFilter;
import edu.miu.shopmartbackend.authfilters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/authenticate");
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/authenticate/**", "/api/v1/users/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers( "/api/v1/users/addUser").hasAuthority("BUYER");
        http.authorizeRequests().antMatchers("/api/v1/roles/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/api/v1/users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers( "/api/v1/reviews/**").hasAuthority("BUYER");
        http.authorizeRequests().antMatchers( "/api/v1/carts/**").hasAuthority("BUYER");



        http.authorizeRequests().antMatchers( "/api/v1/address/**").hasAuthority("BUYER");
        http.authorizeRequests().antMatchers( "/api/v1/buyers/**").hasAuthority("BUYER");
        http.authorizeRequests().antMatchers("GET, DELETE, PATCH","/api/v1/orders/**").hasAuthority("SELLER"); // check??
        http.authorizeRequests().antMatchers("/api/v1/products/**").hasAuthority("SELLER");
        http.authorizeRequests().antMatchers(GET,"/api/v1/products/**").hasAuthority("BUYER");
        http.authorizeRequests().antMatchers("/api/v1/orders/**").hasAuthority("BUYER");


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    Enable cors globally
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
