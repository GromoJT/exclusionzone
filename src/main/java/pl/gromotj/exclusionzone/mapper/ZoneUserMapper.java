package pl.gromotj.exclusionzone.mapper;

import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.entity.ZoneUser;

public class ZoneUserMapper {
    public static ZoneUserDto mapToZoneUserDto(ZoneUser zoneUser){
        return new ZoneUserDto(
                zoneUser.getId(),
                zoneUser.getUserName(),
                zoneUser.getLoreName(),
                zoneUser.getLoreSurname(),
                zoneUser.getPassword(),
                zoneUser.getGlobalRole(),
                zoneUser.getEmail(),
                zoneUser.isRedacted(),
                zoneUser.getCreatedAt(),
                zoneUser.isVerified(),
                zoneUser.getVTokens(),
                zoneUser.getCreatedArtifacts(),
                zoneUser.getCreatedAnomalies(),
                zoneUser.getCreatedLocations(),
                zoneUser.getCreatedCharacters(),
                zoneUser.getCreatedWildlife()
        );
    }

    public static ZoneUser mapToZoneUser(ZoneUserDto zoneUserDto){
        return new ZoneUser(
                zoneUserDto.getId(),
                zoneUserDto.getUserName(),
                zoneUserDto.getLoreName(),
                zoneUserDto.getLoreSurname(),
                zoneUserDto.getPassword(),
                zoneUserDto.getGlobalRole(),
                zoneUserDto.getEmail(),
                zoneUserDto.isRedacted(),
                zoneUserDto.getCreatedAt(),
                zoneUserDto.isVerified(),
                zoneUserDto.getVTokens(),
                zoneUserDto.getCreatedAnomalies(),
                zoneUserDto.getCreatedArtifacts(),
                zoneUserDto.getCreatedLocations(),
                zoneUserDto.getCreatedCharacters(),
                zoneUserDto.getCreatedWildlife()
        );
    }


    public static ZoneUser mapRegisterUserDtoToZoneUser(RegisterZoneUserDto registerZoneUserDto) {
        return new ZoneUser(
                registerZoneUserDto.getUserName(),
                registerZoneUserDto.getLoreName(),
                registerZoneUserDto.getLoreSurname(),
                registerZoneUserDto.getEmail(),
                registerZoneUserDto.getGlobalRole(),
                registerZoneUserDto.getPassword()
        );
    }
}
