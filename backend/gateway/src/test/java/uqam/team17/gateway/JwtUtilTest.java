package uqam.team17.gateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import io.jsonwebtoken.io.Decoders;
import uqam.team17.gateway.util.JwtUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    private String generateTestToken() {
        // Reusing the same secret key and method to obtain the signing key
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtUtil.SECRET));
        return Jwts.builder()
                .setSubject("user")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    public void validateToken_withValidToken_shouldNotThrowException() {
        String token = generateTestToken();
        assertDoesNotThrow(() -> jwtUtil.validateToken(token), "Token should be valid and not throw any exception.");
    }

    @Test
    public void validateToken_withInvalidToken_shouldThrowException() {
        // Purposefully creating a token with an incorrect key
        Key wrongKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String invalidToken = Jwts.builder()
                .setSubject("user")
                .signWith(wrongKey)
                .compact();

        assertThrows(Exception.class, () -> jwtUtil.validateToken(invalidToken), "Invalid token should throw an exception.");
    }
}
