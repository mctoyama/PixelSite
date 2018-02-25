package org.pixelndice.table.pixelsite.config

import org.pixelndice.table.pixelsite.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val accountService: AccountService? = null

    @Throws(Exception::class)
    protected override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers(
                        "/registration",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val auth = DaoAuthenticationProvider()
        auth.setUserDetailsService(accountService)
        auth.setPasswordEncoder(passwordEncoder())
        return auth
    }

    @Throws(Exception::class)
    protected override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider())
    }

}