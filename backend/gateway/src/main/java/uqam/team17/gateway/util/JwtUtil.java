package uqam.team17.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    private final RestTemplate restTemplate;

    public JwtUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public boolean validateTokenWithService(String token) {
        // Replace the URL with the actual authentication service endpoint
        String authServiceUrl = "http://auth-service-url/validate-token";

        // Create a request body containing the token
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);

        try {
            // Send a POST request to the authentication service
            ResponseEntity<Void> response = this.restTemplate.postForEntity(authServiceUrl, requestBody, Void.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
