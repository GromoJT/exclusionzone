package pl.gromotj.exclusionzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserServiceImpl;

@RestController
@RequestMapping("/api/v1/zone-users")
public class ZoneUserController {
    @Autowired
    private ZoneUserServiceImpl zoneUserService;


    //Build Add ZoneUser REST API
    @PostMapping
    public ResponseEntity<ZoneUserDto> createZoneUser(@RequestBody RegisterZoneUserDto registerZoneUserDto){
        ZoneUserDto saveZoneUser = zoneUserService.createZoneUser(registerZoneUserDto);
        return new ResponseEntity<>(saveZoneUser, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<String> responseToEndpoint(){
        return ResponseEntity.ok("OK");
    }
}
