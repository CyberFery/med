package uqam.team17.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.repository.AuthCredentialsRepository;

@Service
public class AuthService {
    @Autowired
    private AuthCredentialsRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Transactional
    public String saveUser(AuthCredentials credentials) {
        // Check if both username & email already exist
        if (repository.findByEmail(credentials.getEmail()).isPresent()
                && repository.findByUsername(credentials.getUsername()).isPresent()) {

            return "There is already an account associated with this username and email.";
        }
        // Check if username already exists
        else if (repository.findByUsername(credentials.getUsername()).isPresent()) {
            return "There is already an account associated with this username.";
        }
        // Check if email already exists
        else if (repository.findByEmail(credentials.getEmail()).isPresent()) {
            return "There is already an account associated with this email.";
        }


        // If no duplicates, proceed to save the new user
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        repository.save(credentials);
        return "User added to the system successfully.";
    }

    public String GenerateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void ValidateToken(String token) {
        jwtService.validateToken(token);
    }
}
