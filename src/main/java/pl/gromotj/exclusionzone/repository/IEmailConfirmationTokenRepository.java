package pl.gromotj.exclusionzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;

@Repository
public interface IEmailConfirmationTokenRepository  extends JpaRepository<EmailConfirmationToken,Long> {
    EmailConfirmationToken findByToken(final String token);
    Long removeByToken(String token);
}
