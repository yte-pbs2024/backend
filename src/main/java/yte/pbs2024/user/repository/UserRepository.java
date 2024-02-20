package yte.pbs2024.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.pbs2024.user.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
