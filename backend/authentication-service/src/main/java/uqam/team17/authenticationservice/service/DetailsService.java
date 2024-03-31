package uqam.team17.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uqam.team17.authenticationservice.config.DetailsConfig;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.repository.AuthCredentialsRepository;

import java.util.Optional;

@Component

public class DetailsService implements UserDetailsService {
    @Autowired
    private AuthCredentialsRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthCredentials> credentials= repository.findByUsername(username);
        return credentials.map(DetailsConfig::new).orElseThrow(()->new UsernameNotFoundException("user not found with name:"+ username));
    }
}
