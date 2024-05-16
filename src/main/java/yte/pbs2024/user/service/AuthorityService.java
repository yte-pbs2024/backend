package yte.pbs2024.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yte.pbs2024.user.entity.Authority;
import yte.pbs2024.user.repository.AuthorityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    public List<String> getAllAuthorityNames() {
        return authorityRepository.findAll().stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toList());
    }

    public List<Long> getAllAuthorityIds() {
        return authorityRepository.findAll().stream()
                .map(Authority::getId)
                .collect(Collectors.toList());
    }

    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

}
