package pl.gromotj.exclusionzone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private ZoneUserDetailService zoneUserDetailService;
    @Autowired
    private  JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(
                            "/api/v1/register/**",
                            "/api/v1/authenticate",
                            "/api/v1/register/zone-user",
                            "/register/**",
                            "/api-docs",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/api/v1/**"
                    ).permitAll();
                    //registry.requestMatchers("/api/v1/**").hasRole("ADMIN");
                    registry.anyRequest().authenticated();
                })
                .formLogin(
                        AbstractAuthenticationFilterConfigurer::permitAll //Default
                        //formLogin -> formLogin
                        //.loginPage("/login")
                        //.permitAll()
                        )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return zoneUserDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(zoneUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(authenticationProvider());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
