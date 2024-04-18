package uqam.team17.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.factory.TokenFactory;
import uqam.team17.authenticationservice.repository.AuthCredentialsRepository;

@Service
public class AuthService {
    @Autowired
    private AuthCredentialsRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenFactory tokenFactory;

    @Transactional
    public String saveUser(AuthCredentials credentials) {
        if (repository.findByEmail(credentials.getEmail()).isPresent()
                && repository.findByUsername(credentials.getUsername()).isPresent()) {
            return "There is already an account associated with this username and email.";
        } else if (repository.findByUsername(credentials.getUsername()).isPresent()) {
            return "There is already an account associated with this username.";
        } else if (repository.findByEmail(credentials.getEmail()).isPresent()) {
            return "There is already an account associated with this email.";
        }

        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        repository.save(credentials);
        return "User added to the system successfully.";
    }

    public String GenerateToken(String username) {
        AuthCredentials credentials = new AuthCredentials();
        credentials.setUsername(username);
        return tokenFactory.generateToken(credentials);
    }

    public void ValidateToken(String token) throws Exception {
        tokenFactory.validateToken(token);
    }
}
