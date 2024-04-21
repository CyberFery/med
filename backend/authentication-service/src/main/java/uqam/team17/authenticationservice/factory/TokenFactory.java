package uqam.team17.authenticationservice.factory;

import uqam.team17.authenticationservice.entity.AuthCredentials;

public interface TokenFactory {
    String generateToken(AuthCredentials credentials);
    void validateToken(String token) throws Exception;
}
