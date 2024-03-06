package yte.pbs2024.user.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.entity.Users;
import yte.pbs2024.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){
        userRepository.save(new Users("user","user","user",
                passwordEncoder.encode("user"),"user","user",
                List.of(new Authority("ROLE_USER"))));
        userRepository.save(new Users("standardUser","standardUser","standardUser",
                passwordEncoder.encode("standardUser"),null,null,
                List.of(new Authority("ROLE_STANDARD_USER"))));
        userRepository.save(new Users("authorizedUser","authorizedUser","authorizedUser",
                passwordEncoder.encode("authorizedUser"),"authorizedUser@gmail.com","authorizedUser",
                List.of(new Authority("ROLE_AUTHORIZED_USER"))));
        userRepository.save(new Users("superUser","superUser","superUser",
                passwordEncoder.encode("superUser"),"superUser@gmail.com","superUser",
                List.of(new Authority("ROLE_SUPER_USER"))));
        userRepository.save(new Users("admin","admin","admin",
                passwordEncoder.encode("admin"),"admin","admin",
                List.of(new Authority("ROLE_ADMIN"))));
    }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username)
                    .map(user -> {
                        List<Authority> authorities = user.getAuthorities();
                        authorities.forEach(authority -> {
                            System.out.println("Role: " + authority.getAuthority());
                        });
                        return user;
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("User with username %s is not present".formatted(username)));
}
}

