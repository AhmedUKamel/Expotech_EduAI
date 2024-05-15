package org.ahmedukamel.eduai.customizer;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class AuthorizeHttpRequestsCustomizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers("/", "index.html").permitAll();
        registry.requestMatchers(
                new AntPathRequestMatcher("/**/auth/**"),
                new AntPathRequestMatcher("/**/public/**", HttpMethod.GET.name())
        ).permitAll();
        registry.anyRequest().authenticated();
    }
}