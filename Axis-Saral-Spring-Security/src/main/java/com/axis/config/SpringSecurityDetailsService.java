package com.axis.config;

import com.axis.entity.SpringSecurity;
import com.axis.repository.SpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityDetailsService implements UserDetailsService {
    @Autowired
    private SpringRepository springRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SpringSecurity> springSecurity = springRepository.findByName(username);
        return springSecurity.map(SpringInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
