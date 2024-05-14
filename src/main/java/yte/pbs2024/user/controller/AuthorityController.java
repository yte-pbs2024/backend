package yte.pbs2024.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.pbs2024.user.controller.response.AuthorityResponse;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.service.AuthorityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorities")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping("/standard-user")
    @PreAuthorize("hasAnyRole('STANDARD_USER','AUTHORIZED_USER','SUPER_USER','ADMIN')")
    public String standard_user(){
        return "standard user";
    }
    @GetMapping("/authorized-user")
    @PreAuthorize("hasAnyRole('AUTHORIZED_USER','SUPER_USER','ADMIN')")
    public String authorize_user(){
        return "authorized user";
    }
    @GetMapping("/super-user")
    @PreAuthorize("hasAnyRole('SUPER_USER','ADMIN')")
    public String super_user(){
        return "super user";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() { return "admin"; }
    @GetMapping
    public List<AuthorityResponse> getAuthority() {
        List<Authority> authorities = authorityService.getAuthorities();
        return authorities.stream()
                .map(authority -> new AuthorityResponse(authority.getId(), authority.getAuthority()))
                .toList();
    }


}
