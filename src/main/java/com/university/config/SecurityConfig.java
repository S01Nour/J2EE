package com.university.config;

import com.university.service.CustomUserDetailsService;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        // For debugging
        provider.setHideUserNotFoundExceptions(false);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .requestMatchers("/", "/login", "/logout", "/resources/**",
                                "/error", "/api/test/**").permitAll()
                        .anyRequest().authenticated()
//                ).formLogin(form -> form
//                        .loginPage("/login")
//                        .usernameParameter("username")  // This matches your form's name="username"
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/dashboard", true)
//                        .failureHandler((request, response, exception) -> {
//                            System.out.println("Login failed: " + exception.getMessage());
//                            exception.printStackTrace(); // Add this for more detailed logs
//                            response.sendRedirect("/login?error");
//                        })
                ).formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform-login") // Change this
                        .failureHandler((request, response, exception) -> {
                            System.out.println("LOGIN FAILED: " + exception.getClass().getName());
                            System.out.println("EXCEPTION MESSAGE: " + exception.getMessage());
                            exception.printStackTrace();
                            response.sendRedirect("/login?error=" + exception.getClass().getSimpleName());
                        })
                        .defaultSuccessUrl("/dashboard", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}




//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(StudentService studentService) {
//        return studentService::findByEmail;
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/", "/home", "/login", "/error", "/css/**", "/js/**", "/images/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .defaultSuccessUrl("/dashboard", true)
////                        .failureUrl("/login?error")
////                        .permitAll()
////                )
////                .logout(logout -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/login?logout")
////                        .permitAll()
////                )
////                .exceptionHandling(exception -> exception
////                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
////                );
////
////        return http.build();
////    }
////}
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/student/**").hasRole("STUDENT")
//                        .requestMatchers("/", "/login", "/logout", "/resources/**",
//                                "/error", "/api/test/**").permitAll()
//                        .anyRequest().authenticated()
////                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .defaultSuccessUrl("/dashboard")
////                        .failureUrl("/login?error")
////                        .permitAll()
//                ).formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/dashboard", true)
//                        .failureHandler((request, response, exception) -> {
//                            System.out.println("Login failed: " + exception.getMessage());
//                            response.sendRedirect("/login?error");
//                        })
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//                )
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
//
//
//@Bean
//public AuthenticationProvider authenticationProvider(StudentService studentService) {
//    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    provider.setUserDetailsService(studentService::findByEmail);
//    provider.setPasswordEncoder(passwordEncoder());
//
//    // Add this line for debugging
//    provider.setHideUserNotFoundExceptions(false);
//
//    return provider;
//}
//
//}