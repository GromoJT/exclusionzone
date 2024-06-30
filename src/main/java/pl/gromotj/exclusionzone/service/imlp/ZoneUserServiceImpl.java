package pl.gromotj.exclusionzone.service.imlp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.exception.ResourceNotFoundException;
import pl.gromotj.exclusionzone.mapper.EntryMapper;
import pl.gromotj.exclusionzone.mapper.ZoneUserMapper;
import pl.gromotj.exclusionzone.repository.IEmailConfirmationTokenRepository;
import pl.gromotj.exclusionzone.repository.IZoneUserRepository;
import pl.gromotj.exclusionzone.service.EmailService;
import pl.gromotj.exclusionzone.service.IZoneUserService;

@Slf4j
@Service
@AllArgsConstructor
public class ZoneUserServiceImpl implements IZoneUserService {
    @Autowired
    private EntryServiceImplementation entryServiceImplementation;

    @Autowired
    private EntryMapper entryMapper;

    @Autowired
    private EmailService emailService;

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
        String msg = String.format("Click this LINK: http://localhost:8080/api/v1/register/verify/%s",eToken.getToken());
        emailService.sendEmail(zoneUser.getEmail(),"Project \"closed zone\" account verification",msg);
    }


    @Override
    public ZoneUserDto getZoneUserById(String zoneUserId) {
        ZoneUser zoneUser = zoneUserRepository.findById(zoneUserId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                String.format("ZoneUser with given id : %s, does not exists",zoneUserId)));
        return ZoneUserMapper.mapToZoneUserDto(zoneUser);
    }

//    public void addEntryToUser(String id, String saveEntryId) {
//        ZoneUser zoneUser = zoneUserRepository.findById(id)
//                .orElseThrow(
//                        ()-> new ResourceNotFoundException(
//                                String.format("ZoneUser with given id : %s, does not exists",id)));
//        zoneUser.getCreatedEntries().add(EntryMapper.mapToEntry(entryServiceImplementation.getEntryById(saveEntryId)));
//    }
}
