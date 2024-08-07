package tyut.selab.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tyut.selab.framework.jwt.header.JwtAccessDeniedHandler;
import tyut.selab.framework.jwt.header.JwtLogoutSuccessHandler;
import tyut.selab.framework.jwt.header.LoginFailureHandler;
import tyut.selab.framework.jwt.header.LoginSuccessHandler;
import tyut.selab.framework.jwt.security.JwtAuthenticationEntryPoint;
import tyut.selab.framework.jwt.security.JwtAuthenticationFilter;
import tyut.selab.framework.web.service.AccountUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @ClassName: SecurityConfig
 * @Description:Security集成
 * @Author: gmslymhn
 * @CreateTime: 2024-05-19 18:55
 * @Version: 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private AccountUserDetailsService accountUserDetailsService;
    private static final String[] URL_WHITELIST = {"foreign/*","/background/login","/background/register","/background/register/verify","/background/resource/getResourceByLz","/favicon.ico","/doc.html","/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs/**", "/druid/**"};

//    private static final String[] URL_WHITELIST = {"foreign/*","/background/login","/background/register","/background/register/verify","/background/resource/getResourceByLz"};
    private static final String[] URL_WHITELIST_GET = {"/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**","/webjars/**"};
//    private static final String[] URL_WHITELIST_GET = {};


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return 身份校验机制、身份验证提供程序
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // 创建一个用户认证提供者
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // 设置用户相关信息，可以从数据库中读取、或者缓存、或者配置文件
        authProvider.setUserDetailsService(accountUserDetailsService);
        // 设置加密机制，用于对用户进行身份验证
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    /**
     * 基于用户名和密码或使用用户名和密码进行身份验证
     *
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                // 禁用csrf(防止跨站请求伪造攻击)
                .csrf(csrf -> csrf.disable())
                // 登录操作
                .formLogin(form -> form.successHandler(loginSuccessHandler).failureHandler(loginFailureHandler))
                // 登出操作
                .logout(logout -> logout.logoutSuccessHandler(jwtLogoutSuccessHandler))
                // 使用无状态session，即不使用session缓存数据
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 设置白名单
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(URL_WHITELIST).permitAll().anyRequest().authenticated())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(URL_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.GET, URL_WHITELIST_GET).permitAll()
                        .anyRequest().authenticated())
                // 异常处理器
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler))
                // 添加jwt过滤器
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
