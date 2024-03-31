package uqam.team17.authenticationservice.controller;

import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uqam.team17.authenticationservice.dto.AuthRequest;
import uqam.team17.authenticationservice.entity.AuthCredentials;
import uqam.team17.authenticationservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager AuthManager;
    @Autowired
    private AuthService AuthService;


    @PostMapping("/register")
    public String addNewUser(@RequestBody AuthCredentials credentials){
        return AuthService.saveUser(credentials);
    }
    @PostMapping("/token")

    public String getToken(@RequestBody AuthRequest AuthRequest){
        Authentication authenticate = AuthManager.authenticate(new UsernamePasswordAuthenticationToken(AuthRequest.getUsername(), AuthRequest.getPassword()));
        if (authenticate.isAuthenticated()){
        return AuthService.GenerateToken(AuthRequest.getUsername());
        } else throw new RuntimeException("Invalid Credentials");
    }

    @GetMapping("/validate")

    public String validateToken(@RequestParam("token") String token){
        AuthService.ValidateToken(token);
        return "Token is Valid";
    }


}
