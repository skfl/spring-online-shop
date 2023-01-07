package ru.skfl.skflshop.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .rememberMe()
//                .rememberMeParameter("rememberMe")
//                .tokenRepository(tokenRepository())
//                .tokenValiditySeconds(60 * 60 * 24 * 365)
//                .and()
                    .authorizeRequests()
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/shipping").authenticated()
                    .antMatchers("/accounts/**").hasAuthority("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .defaultSuccessUrl("/")
                    .failureUrl("/signIn?error")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/signIn?logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
        http.csrf().disable();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}

