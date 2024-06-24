package pl.gromotj.exclusionzone.service;

import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;

public interface IDefaultEmailVerificationTokenService {
    EmailConfirmationToken createEmailConfirmationToken();
    void saveEmailConfirmationToken(EmailConfirmationToken eToken);
    EmailConfirmationToken findByToken(String eToken);
    void removeToken(EmailConfirmationToken eToken);
    void removeTokenByToken(String eToken);
}
