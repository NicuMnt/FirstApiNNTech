import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // ✅ Disable CSRF to allow DELETE requests
                .authorizeRequests()
                .antMatchers("/users/**").permitAll()  // ✅ Allow all requests to /users
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
    }
}
