package pl.gromotj.exclusionzone.service.imlp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.exception.ResourceNotFoundException;
import pl.gromotj.exclusionzone.mapper.ZoneUserMapper;
import pl.gromotj.exclusionzone.repository.IEmailConfirmationTokenRepository;
import pl.gromotj.exclusionzone.repository.IZoneUserRepository;
import pl.gromotj.exclusionzone.service.IDefaultEmailVerificationTokenService;
import pl.gromotj.exclusionzone.service.IZoneUserService;

import java.util.logging.Logger;
@Slf4j
@Service
@AllArgsConstructor
public class ZoneUserServiceImpl implements IZoneUserService {



    @Autowired
    private IZoneUserRepository zoneUserRepository;

    @Autowired
    private IEmailConfirmationTokenRepository emailConfirmationTokenRepository;

    @Autowired
    private DefaultEmailVerificationTokenServiceImpl emailVerificationTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ZoneUserDto createZoneUser(RegisterZoneUserDto registerZoneUserDto) {
        ZoneUser zoneUser = ZoneUserMapper.mapRegisterUserDtoToZoneUser(registerZoneUserDto);
        zoneUser.setPassword(passwordEncoder.encode(zoneUser.getPassword()));
        ZoneUser saveZoneUser = zoneUserRepository.save(zoneUser);
        sendRegistrationConfirmationEmail(saveZoneUser);
        return ZoneUserMapper.mapToZoneUserDto(saveZoneUser);
    }

    public void sendRegistrationConfirmationEmail(ZoneUser zoneUser){

        EmailConfirmationToken eToken = emailVerificationTokenService.createEmailConfirmationToken();
        eToken.setZoneUser(zoneUser);
        emailConfirmationTokenRepository.save(eToken);

    }


//    @Override
//    public ZoneUserDto getZoneUserById(Long zoneUserId) {
//        ZoneUser zoneUser = zoneUserRepository.findByUserId(zoneUserId)
//                .orElseThrow(
//                        ()-> new ResourceNotFoundException(
//                                String.format("ZoneUser with given id : %s, does not exists",zoneUserId)));
//        return ZoneUserMapper.mapToZoneUserDto(zoneUser);
//    }
}
