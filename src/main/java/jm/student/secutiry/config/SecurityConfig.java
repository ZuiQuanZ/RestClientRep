package jm.student.secutiry.config;

import jm.student.secutiry.handlers.AuthFailHandler;
import jm.student.secutiry.handlers.AuthSuccessHandler;
import jm.student.secutiry.handlers.LogoutSuccessHandler;
import jm.student.secutiry.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan("jm.student")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl authService;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailHandler authFailHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl authService,
                          AuthSuccessHandler authSuccessHandler,
                          AuthFailHandler authFailHandler,
                          LogoutSuccessHandler logoutSuccessHandler) {
        this.authService = authService;
        this.authSuccessHandler = authSuccessHandler;
        this.authFailHandler = authFailHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.csrf().disable().addFilterBefore(filter, CsrfFilter.class);
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyAuthority("USER")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/processing-url")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler)
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/error");
    }

}
