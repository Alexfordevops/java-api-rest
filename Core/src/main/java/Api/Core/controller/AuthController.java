package Api.Core.controller;

import Api.Core.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import Api.Core.service.CustomUserDetailsService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails user = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(user.getUsername());

        return Map.of("token", token);
    }
}
