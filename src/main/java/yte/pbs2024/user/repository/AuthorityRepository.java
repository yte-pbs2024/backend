package yte.pbs2024.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.pbs2024.user.entity.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findById(long id);

}