package yte.pbs2024.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.pbs2024.user.controller.response.AuthorityResponse;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.service.AuthorityService;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authorities")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorityController {
    private final AuthorityService authorityService;
    @GetMapping
    public List<AuthorityResponse> getAuthority() {
        List<Authority> authorities = authorityService.getAuthorities();
        return authorities.stream()
                .map(authority -> new AuthorityResponse(authority.getId(), authority.getAuthority()))
                .toList();
    }
}