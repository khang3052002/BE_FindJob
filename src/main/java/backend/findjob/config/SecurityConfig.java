package backend.findjob.config;

import backend.findjob.config.jwt.JwtAuthenticationFilter;
import backend.findjob.dto.respone.ResponeObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;
    private  final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .httpBasic(withDefaults())
                .authorizeHttpRequests(
                        authCustomizer -> authCustomizer
//                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/**","/v3/api-docs/**", "/swagger-ui/**").permitAll()
//                        .requestMatchers("/api/doibong/**").hasAuthority("QLDB")
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers(PUT,"/api/test").hasAnyAuthority("QLDB")
//                        .anyRequest()
//                        .authenticated()
                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(
//                        logout -> logout.logoutUrl("/api/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler(((request, response, authentication) ->
//                                {
//                                    response.setStatus(HttpStatus.OK.value());
//                                    response.setContentType(APPLICATION_JSON_VALUE);
//                                    ResponeObject responeObj = new ResponeObject("OK","Đăng xuất thành công","");
//                                    SecurityContextHolder.clearContext();
//                                    new ObjectMapper().writeValue(response.getOutputStream(),responeObj);
//
//                                }
//                                ))
//                );
        ;


        return http.build();
    }
}
