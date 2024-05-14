package yte.pbs2024.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.repository.AuthorityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public List<Authority> getAuthorities() {
        return(authorityRepository.findAll());
    }

}
