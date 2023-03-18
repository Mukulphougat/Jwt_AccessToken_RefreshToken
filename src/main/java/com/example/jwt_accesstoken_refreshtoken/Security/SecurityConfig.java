package com.example.jwt_accesstoken_refreshtoken.Security;

import com.example.jwt_accesstoken_refreshtoken.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private UserService userService;
    @Autowired
    private AccessTokenEntryPoint accessTokenEntryPoint;
    @Bean
    public AccessTokenFilter accessTokenFilter(){
        return new AccessTokenFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf().disable();
        security.cors();
        security.exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint);
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.authorizeHttpRequests().antMatchers(new String[]{"/api/auth/**","/testing/**","/swagger-ui/**","/v3/api-docs/**","/swagger-ui-custom.html"}).permitAll().anyRequest().authenticated();
        security.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }
//
//    @Bean
//    public CorsConfigurationSource corsConfiguration(){
//        CorsConfiguration corsConfiguration= new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:5173/"));
//        corsConfiguration.addAllowedMethod("/**");
//        UrlBasedCorsConfigurationSource configurationSource=new UrlBasedCorsConfigurationSource();
//        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
//        return configurationSource;
//    }
}
