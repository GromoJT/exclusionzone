package pl.gromotj.exclusionzone.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromotj.exclusionzone.entity.ZoneUser;

import java.util.Optional;
@Repository
@Transactional
public interface IZoneUserRepository extends JpaRepository<ZoneUser,String> {
    Optional<ZoneUser> findByUserName(String userName);
    Optional<ZoneUser> findByEmail(String email);
    Optional<ZoneUser> findById(String zoneUserId);

}
