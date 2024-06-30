package pl.gromotj.exclusionzone.service;

import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.entity.ZoneUser;

public interface IZoneUserService {
    ZoneUserDto createZoneUser(RegisterZoneUserDto registerZoneUserDto);

    ZoneUserDto getZoneUserById(String zoneUserId);

    //ZoneUserDto getZoneUserById(Long zoneUserId);
}
