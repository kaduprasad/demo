package spring.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import spring.demo.repo.UserRepo;
import spring.demo.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepo userRepo) {
        return new CustomUserDetailsService(userRepo);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        System.out.println("Inside authenticationProvider");

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Inside filterChain");

        http.authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/demo/all", "/demo/add").permitAll()  // Allow public access to these endpoints
                                .anyRequest().authenticated()  // All other requests require authentication
                )
                .httpBasic(Customizer.withDefaults())// Enable form-based login
                .csrf(AbstractHttpConfigurer::disable);  // Disable CSRF protection (optional)  // Disable CSRF protection for simplicity (optional)

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        System.out.println("Inside authenticationManager");
        return authConfig.getAuthenticationManager();
    }





    /**
     * Bean Annotation: Marks the method as a bean producer, so the Spring IoC container will register the returned AuthenticationManager as a bean.
     * This makes it available for dependency injection throughout the application.
     */
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class) // This builder is used to set up authentication configurations.
//                .userDetailsService(userDetailsService)                // Registers the injected UserDetailsService, telling Spring Security to use this service to load user details during authentication.
//                .passwordEncoder(passwordEncoder)
//                .build();
//    }
}
