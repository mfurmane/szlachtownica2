package priv.mfurmane.szlachtownica.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }

//    @Bean
//    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
//        return new JwtAuthFilter(jwtService, userDetailsService);
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, JwtService jwtService, UserDetailsService userDetailsService) throws Exception {
//        http
//                .cors(Customizer.withDefaults()) // Umożliwia obsługę CORS
//                .csrf().disable()
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/public/**").permitAll()
//                        .requestMatchers("/mercenary/**").hasRole("USER")
//                        .requestMatchers("/shopkeeper/**").hasRole("SHOPKEEPER")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/major/**").hasRole("MAJOR")
//                        .requestMatchers("/wantedManager/**").hasAnyRole("MAJOR", "ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtAuthFilter(jwtService, userDetailsService), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return username -> {
//            User user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getRoles().stream()
//                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                            .toList()
//            );
//        };
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    // 🔥 Konfiguracja CORS dla całej aplikacji (współgra z frontendem Angular)
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        //                                  SIEĆ Z TELEFONU              KUŹNICY KOŁŁĄTAJOWSKIEJ            RADZIĘCIN         KURWA NIE MAM POJĘCIA, ALE TEŻ TELEFON
//        config.setAllowedOrigins(List.of("http://192.168.197.79:4200", "http://192.168.0.206:4200", "http://192.168.8.196:4200", "http://192.168.129.79:4200", "http://localhost:4200")); // 🔧 Na testy – produkcyjnie podaj domenę frontu np. http://localhost:4200
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true); // jeśli logowanie z Basic Auth lub JWT z cookies
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
}
