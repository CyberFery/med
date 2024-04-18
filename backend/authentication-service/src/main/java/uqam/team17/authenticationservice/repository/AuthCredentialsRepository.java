package uqam.team17.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uqam.team17.authenticationservice.entity.AuthCredentials;

import java.util.Optional;

public interface AuthCredentialsRepository extends JpaRepository<AuthCredentials, Integer> {

    Optional<AuthCredentials> findByUsername(String username);
    Optional<AuthCredentials> findByEmail(String email);
}
