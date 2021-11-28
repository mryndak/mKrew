package pl.mkrew.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mkrew.app.repository.UserRepository;
import pl.mkrew.app.security.MkrewUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    private static final String[] ANONYMOUS_LIST = {
            "/v1/user/confirmation/**",
            "/v1/user/registration",
            "/v1/blood-supplies/refresh",
            "/v1/appointment/**",
            "/v1/forgot",
            "/v1/reset",
            "/v1/user/**"
    };
    // lista będzie powiększona w miarę potrzeb
    private  static  final String[] AUTH_USER_ROLE_LIST = {
            "/v1/user",
            "/v1/home"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(ANONYMOUS_LIST).permitAll()
                .antMatchers(AUTH_USER_ROLE_LIST).hasRole("USER")
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/v1/login").permitAll()
                .loginProcessingUrl("/v1/login")
                .successForwardUrl("/v1/home")
                .and()
                .logout(logout -> logout
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/v1/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authConfigProvider());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authConfigProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        UserDetailsService userDetailsService = new MkrewUserDetailsService(userRepository);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
