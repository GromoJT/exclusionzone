package pl.gromotj.exclusionzone.service.imlp;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.repository.IEmailConfirmationTokenRepository;
import pl.gromotj.exclusionzone.repository.IZoneUserRepository;
import pl.gromotj.exclusionzone.service.IDefaultEmailVerificationTokenService;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Base64;
@Service
@AllArgsConstructor
public class DefaultEmailVerificationTokenServiceImpl implements IDefaultEmailVerificationTokenService {
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom();
    private static final Charset US_ASCII = Charset.forName("US-ASCII");


    @Autowired
    IEmailConfirmationTokenRepository emailConfirmationTokenRepository;

    @Autowired
    IZoneUserRepository zoneUserRepository;
    @Override
    public EmailConfirmationToken createEmailConfirmationToken() {
        String tokenValue = new String(Base64.getUrlEncoder().encodeToString(DEFAULT_TOKEN_GENERATOR.generateKey()));
        EmailConfirmationToken eToken = new EmailConfirmationToken();
        eToken.setToken(tokenValue);
        eToken.setExpireAt(ZonedDateTime.now().plusMinutes(60*24));
        this.saveEmailConfirmationToken(eToken);
        return eToken;
    }

    @Override
    public void saveEmailConfirmationToken(EmailConfirmationToken eToken) {
        emailConfirmationTokenRepository.save(eToken);
    }

    @Override
    public EmailConfirmationToken findByToken(String eToken) {
        return emailConfirmationTokenRepository.findByToken(eToken);
    }

    @Override
    public void removeToken(EmailConfirmationToken eToken) {
        emailConfirmationTokenRepository.delete(eToken);
    }

    @Override
    public void removeTokenByToken(String eToken) {
        emailConfirmationTokenRepository.removeByToken(eToken);
    }

    @Override
    public boolean verifyToken(String eToken) {
        EmailConfirmationToken token = findByToken(eToken);
        if(ZonedDateTime.now().isBefore(token.getExpireAt())){
            ZoneUser verifiedUser = token.getZoneUser();
            if(verifiedUser!=null){
                verifiedUser.setVerified(true);
                zoneUserRepository.save(verifiedUser);
                emailConfirmationTokenRepository.delete(token);
                return true;
            }
            emailConfirmationTokenRepository.delete(token);
        }
        return false;
    }


}
