package uqam.team17.authenticationservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import uqam.team17.authenticationservice.controller.AuthController;
import uqam.team17.authenticationservice.dto.AuthRequest;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.service.AuthService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authManager;
    @MockBean
    private AuthService authService;

    @BeforeEach
    public void setup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testGetToken_Success() throws Exception {
        AuthRequest authRequest = new AuthRequest("user", "password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword(), null);
        when(authManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(authService.GenerateToken(authRequest.getUsername())).thenReturn("token123");

        mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("token123"));
    }

    @Test
    public void testGetToken_Failure() throws Exception {
        when(authManager.authenticate(any(Authentication.class))).thenThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user\",\"password\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Authentication failed: Invalid credentials"));
    }

    @Test
    public void testRegister_NewUser() throws Exception {
        when(authService.saveUser(any(AuthCredentials.class))).thenReturn("User added to the system successfully.");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"newUser\",\"email\":\"newUser@example.com\",\"password\":\"pass\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User added to the system successfully."));
    }

    @Test
    public void testRegister_UsernameAlreadyExists() throws Exception {
        when(authService.saveUser(any(AuthCredentials.class))).thenReturn("There is already an account associated with this username.");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"existingUser\",\"email\":\"newemail@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("There is already an account associated with this username."));
    }

    @Test
    public void testRegister_EmailAlreadyExists() throws Exception {
        when(authService.saveUser(any(AuthCredentials.class))).thenReturn("There is already an account associated with this email.");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"newUser\",\"email\":\"existing@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("There is already an account associated with this email."));
    }

    @Test
    public void testRegister_UsernameAndEmailAlreadyExist() throws Exception {
        when(authService.saveUser(any(AuthCredentials.class))).thenReturn("There is already an account associated with this username and email.");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"existingUser\",\"email\":\"existing@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("There is already an account associated with this username and email."));
    }

    @Test
    public void testValidateToken_ValidToken() throws Exception {
        mockMvc.perform(get("/auth/validate")
                        .param("token", "validToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("Token is valid"));
    }

    @Test
    public void testValidateToken_InvalidToken() throws Exception {
        doThrow(new IllegalArgumentException("Token validation failed: Invalid token"))
                .when(authService).ValidateToken("invalidToken");

        mockMvc.perform(get("/auth/validate")
                        .param("token", "invalidToken"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Token validation failed:")));
    }

    @TestConfiguration
    public static class TestSecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeRequests()
                    .anyRequest().permitAll();
            return http.build();
        }
    }
}
