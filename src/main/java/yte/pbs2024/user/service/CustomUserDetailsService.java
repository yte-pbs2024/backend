package yte.pbs2024.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username)
                    .map(user -> {
                        List<Authority> authorities = user.getAuthorities();
                        authorities.forEach(authority -> {
                            System.out.println("Role: " + authority.getAuthority());
                        });
                        String encodedPassword = passwordEncoder.encode(user.getPassword());
                        user.setPassword(encodedPassword);
                        return user;
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("User with username %s is not present".formatted(username)));
}
}

