package uqam.team17.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.repository.AuthCredentialsRepository;

@Service
public class AuthService {
    @Autowired
    private AuthCredentialsRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService JwtService;
    public String saveUser (AuthCredentials credentials){
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        repository.save(credentials);
        return "user added to system";
    }

    public String GenerateToken(String username){
        return JwtService.generateToken(username);

    }

    public void ValidateToken(String token){
        JwtService.validateToken(token);
    }

}
