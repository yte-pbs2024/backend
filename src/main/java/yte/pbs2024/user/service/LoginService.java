package yte.pbs2024.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import yte.pbs2024.common.response.MessageType;
import yte.pbs2024.user.controller.request.LoginRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yte.pbs2024.user.controller.response.LoginResponse;
import yte.pbs2024.user.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;


    private void saveContext() {
        if (RequestContextHolder.getRequestAttributes() != null) {
            var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            var response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
        }
    }
    public LoginResponse login(LoginRequest loginRequest) {
        try {

            Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

            if (authenticated.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticated);
                saveContext();

                Users user = (Users) authenticated.getPrincipal();
                List<String> authorities = authenticated.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();
                return new LoginResponse(
                        user.getId(),
                        user.getUsername(),
                        authorities,
                        MessageType.SUCCESS,
                        "Login is Successfully"
                );
            } else {
                return new LoginResponse(
                        null,
                        null,
                        null,
                        MessageType.ERROR,
                        "xx"
                );
            }
        } catch (AuthenticationException ex) {
            return new LoginResponse(
                    null,
                    null,
                    null,
                    MessageType.ERROR,
                    ex.getMessage()
            );
        }
    }
}


