package pl.gromotj.exclusionzone.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.entity.ZoneUser;

import pl.gromotj.exclusionzone.repository.IZoneUserRepository;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserDetailService;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserServiceImpl;
import pl.gromotj.exclusionzone.webtoken.JwtService;
import pl.gromotj.exclusionzone.webtoken.LoginForm;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/register")
public class RegistrationController {



    @Autowired
    private ZoneUserServiceImpl zoneUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/zone-user")
    public ResponseEntity<ZoneUserDto> createZoneUser(@RequestBody RegisterZoneUserDto RegisterZoneUserDto){
        ZoneUserDto saveZoneUser = zoneUserService.createZoneUser(RegisterZoneUserDto);
        return new ResponseEntity<>(saveZoneUser, HttpStatus.CREATED);
    }

    @PostMapping("/verify/{token}")
    public ResponseEntity<String> verifyZoneUser(@PathVariable String token){

        return ResponseEntity.ok("User has been verified!");
    }
}
