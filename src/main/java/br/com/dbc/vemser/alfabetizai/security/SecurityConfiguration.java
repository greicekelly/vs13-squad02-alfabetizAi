package br.com.dbc.vemser.alfabetizai.security;

import br.com.dbc.vemser.alfabetizai.security.TokenAuthenticationFilter;
import br.com.dbc.vemser.alfabetizai.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                                .antMatchers("/auth").permitAll()
                                .antMatchers( "/auth/cadastrar/responsavel").permitAll()
                                .antMatchers( "/auth/cadastrar/professor").permitAll()
                                .antMatchers("/auth/cadastrar/admin").hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT,"/modulo/**").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.POST, "/modulo/**").hasRole("PROFESSOR")
                                .antMatchers(HttpMethod.GET, "/modulo", "/modulo/{idModulo}").hasAnyRole("ADMIN", "RESPONSAVEL", "PROFESSOR")
                                .antMatchers(HttpMethod.GET, "/modulo/professor/**").hasRole( "PROFESSOR")
                                .antMatchers("/modulo/**").hasRole("ADMIN")
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .antMatchers(HttpMethod.DELETE,"/responsavel/delete-fisico").hasRole("ADMIN")
                                .antMatchers(HttpMethod.GET,"/responsavel/logado").hasAnyRole("ADMIN", "RESPONSAVEL")
                                .antMatchers(HttpMethod.PUT,"/responsavel/**").hasAnyRole("ADMIN", "RESPONSAVEL")
                                .antMatchers("/responsavel/**").hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT,"/desafio/**").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.POST,"/desafio").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.GET,"/desafio").hasAnyRole("ADMIN", "PROFESSOR", "RESPONSAVEL")
                                .antMatchers(HttpMethod.GET,"/desafio/**").hasAnyRole("ADMIN", "PROFESSOR", "RESPONSAVEL")
                                .antMatchers("/desafio/**").hasRole("ADMIN")
                                .antMatchers(HttpMethod.DELETE,"/professor/delete-fisico").hasRole("ADMIN")
                                .antMatchers(HttpMethod.DELETE,"/professor").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.PUT,"/professor/**").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.POST,"/professor/**").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.GET,"/professor/logado").hasAnyRole("ADMIN", "PROFESSOR")
                                .antMatchers(HttpMethod.GET,"/professor/**").hasRole("ADMIN")
                                .antMatchers(HttpMethod.DELETE,"/aluno/delete-fisico").hasRole("ADMIN")
                                .antMatchers(HttpMethod.GET,"/aluno").hasRole("ADMIN")
                                .antMatchers("/aluno/**").hasAnyRole("ADMIN", "RESPONSAVEL")
                                .antMatchers("/relatrorio/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
