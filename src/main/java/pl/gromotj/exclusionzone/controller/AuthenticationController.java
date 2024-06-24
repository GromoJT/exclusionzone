package pl.gromotj.exclusionzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserDetailService;
import pl.gromotj.exclusionzone.webtoken.JwtService;
import pl.gromotj.exclusionzone.webtoken.LoginForm;
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ZoneUserDetailService zoneUserDetailService;
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(),loginForm.password()
        ));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(zoneUserDetailService.loadUserByUsername(loginForm.username()));
        }else{
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
