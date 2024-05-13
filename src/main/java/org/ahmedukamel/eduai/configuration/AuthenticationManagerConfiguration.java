package org.ahmedukamel.eduai.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfiguration implements AuthenticationManager {
    final UserDetailsService userDetailsService;
    final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        if (!userDetails.isEnabled()) {
            throw new DisabledException("The account is not activated yet.");
        }

        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("The account is locked by moderators.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}