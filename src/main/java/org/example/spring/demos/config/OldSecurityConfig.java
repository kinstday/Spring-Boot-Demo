package org.example.spring.demos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class OldSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .antMatchers("/login", "/public/**", "/static/**", "/api/**","/actuator/**").permitAll() // 放行路径
                        .antMatchers("/admin/**").hasRole("ADMIN")                    // 角色控制
                        .anyRequest().authenticated()                                  // 其他请求需认证
                )
                .formLogin(form -> form
//                        .loginPage("/login")           // 自定义登录页
                        .defaultSuccessUrl("/home")    // 登录成功后跳转
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")          // 注销路径
                        .logoutSuccessUrl("/login?logout") // 注销后跳转
                        .invalidateHttpSession(true)   // 使 Session 失效
                        .deleteCookies("JSESSIONID")   // 删除 Cookie
                )
                .rememberMe(remember -> remember
                        // 记住我功能
                        .tokenValiditySeconds(86400)   // 有效期 1 天（单位：秒）
                        .key("uniqueAndSecretKey")
                )
                .csrf(csrf -> csrf.disable());     // 关闭 CSRF（仅测试环境用，生产需开启）
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();   // 密码加密器
//    }

}
