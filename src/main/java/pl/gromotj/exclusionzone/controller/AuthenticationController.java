package pl.gromotj.exclusionzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.exception.UserEmailNotFoundException;
import pl.gromotj.exclusionzone.service.imlp.EntryServiceImplementation;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserDetailService;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserServiceImpl;
import pl.gromotj.exclusionzone.webtoken.JwtService;
import pl.gromotj.exclusionzone.webtoken.LoginForm;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ZoneUserDetailService zoneUserDetailService;
    @PostMapping
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
        Optional<UserDetails> zoneUser = Optional.ofNullable(zoneUserDetailService.loadUserByEmail(loginForm.email()));
        if(!zoneUser.isPresent()){ throw new UsernameNotFoundException("Invalid credentials");}

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        zoneUser.get().getUsername(),
                        loginForm.password()
                ));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(zoneUserDetailService.loadUserByEmail(loginForm.email()));
        }else{
            throw new UsernameNotFoundException("Invalid credentials");
        }

    }
    @GetMapping()
    public ResponseEntity<String> test(){
        String x = jwtService.generateToken(zoneUserDetailService.loadUserByUsername("janek.j113@gmail.com"));
        return new ResponseEntity<String>("SUCCESS", HttpStatus.ACCEPTED);
    };
}
