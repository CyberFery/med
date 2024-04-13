package uqam.team17.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import uqam.team17.gateway.filter.RouteValidator;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteValidatorTest {

    private final RouteValidator validator = new RouteValidator();

    @Test
    public void whenRequestIsForOpenEndpoint_thenShouldNotSecure() {
        ServerHttpRequest request = MockServerHttpRequest.get("/auth/token").build();
        boolean isSecured = validator.isSecured.test(request);
        assertThat(isSecured).isFalse();
    }

    @Test
    public void whenRequestIsForSecuredEndpoint_thenShouldSecure() {
        ServerHttpRequest request = MockServerHttpRequest.get("/api/user").build();
        boolean isSecured = validator.isSecured.test(request);
        assertThat(isSecured).isTrue();
    }
}
