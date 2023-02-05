package com.debatemanagement.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
                "invalid Authentication type");

        String userName = authentication.getName();
        String rawPasswordIp = authentication.getCredentials().toString();;
        UserDetails user = userDetailsService.loadUserByUsername(userName);
        String hashPasswordStored = user.getPassword();
        if (passwordEncoder.matches(rawPasswordIp, hashPasswordStored)) {
            var token = new UsernamePasswordAuthenticationToken(userName, rawPasswordIp, user.getAuthorities());
            return token;
        }
        else
            throw new BadCredentialsException("invalid password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}