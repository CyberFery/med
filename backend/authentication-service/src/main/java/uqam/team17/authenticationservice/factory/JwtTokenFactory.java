package uqam.team17.authenticationservice.factory;

import org.springframework.stereotype.Component;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.token.JwtToken;
import uqam.team17.authenticationservice.token.Token;

@Component
public class JwtTokenFactory implements TokenFactory {
    @Override
    public String generateToken(AuthCredentials credentials) {
        Token token = new JwtToken(credentials.getUsername());
        return token.getTokenData();
    }

    @Override
    public void validateToken(String token) throws Exception {
        JwtToken jwtToken = new JwtToken(token);
        jwtToken.validate();
    }
}
