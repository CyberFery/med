package uqam.team17.gateway;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import uqam.team17.gateway.filter.AuthenticationFilter;
import uqam.team17.gateway.filter.RouteValidator;
import uqam.team17.gateway.util.JwtUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthenticationFilterTest {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired // Autowired to use the actual RouteValidator
    private RouteValidator routeValidator;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private GatewayFilterChain filterChain;

    @Test
    public void whenTokenIsMissing_thenUnauthorized() {
        ServerHttpRequest request = MockServerHttpRequest.get("/api/resource").build();
        ServerWebExchange exchange = MockServerWebExchange.from((MockServerHttpRequest) request);

        if (routeValidator.isSecured.test(request)) {
            authenticationFilter.apply(new AuthenticationFilter.Config()).filter(exchange, filterChain).block();
            assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        }
    }

    @Test
    public void whenTokenIsValid_thenProceed() {
        String token = "validToken";
        ServerHttpRequest request = MockServerHttpRequest.get("/api/resource")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
        ServerWebExchange exchange = MockServerWebExchange.from((MockServerHttpRequest) request);

        if (routeValidator.isSecured.test(request)) {
            doNothing().when(jwtUtil).validateToken(token);
            when(filterChain.filter(exchange)).thenReturn(Mono.empty());

            authenticationFilter.apply(new AuthenticationFilter.Config()).filter(exchange, filterChain).block();
            verify(filterChain, times(1)).filter(exchange);
        }
    }

    @Test
    public void whenTokenIsInvalid_thenUnauthorized() {
        String token = "invalidToken";
        ServerHttpRequest request = MockServerHttpRequest.get("/api/resource")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
        ServerWebExchange exchange = MockServerWebExchange.from((MockServerHttpRequest) request);

        if (routeValidator.isSecured.test(request)) {
            doThrow(new RuntimeException("Invalid token")).when(jwtUtil).validateToken(token);

            authenticationFilter.apply(new AuthenticationFilter.Config()).filter(exchange, filterChain).block();
            assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
        }
    }

    @Test
    public void whenRequestIsOnOpenApiEndpoint_thenSkipAuthentication() {
        ServerHttpRequest request = MockServerHttpRequest.get("/auth/register").build();
        ServerWebExchange exchange = MockServerWebExchange.from((MockServerHttpRequest) request);

        if (!routeValidator.isSecured.test(request)) {
            when(filterChain.filter(exchange)).thenReturn(Mono.empty());

            authenticationFilter.apply(new AuthenticationFilter.Config()).filter(exchange, filterChain).block();
            verify(filterChain, times(1)).filter(exchange);
        }
    }
}
