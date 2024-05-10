package yte.pbs2024.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.pbs2024.user.controller.response.AuthorityResponse;
import yte.pbs2024.user.service.AuthorityService;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping("/authorities")
    public List<AuthorityResponse> getAllAuthorities() {
        List<String> authorityNames = authorityService.getAllAuthorityNames();
        List<Long> authorityIds = authorityService.getAllAuthorityIds();

        List<AuthorityResponse> authorityResponses = new ArrayList<>();
        for (int i = 0; i < authorityNames.size(); i++) {
            authorityResponses.add(new AuthorityResponse(authorityIds.get(i), authorityNames.get(i)));
        }

        return authorityResponses;
    }
}