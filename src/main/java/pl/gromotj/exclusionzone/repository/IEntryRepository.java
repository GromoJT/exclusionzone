package pl.gromotj.exclusionzone.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromotj.exclusionzone.entity.Entry;
@Repository
@Transactional
public interface IEntryRepository extends JpaRepository<Entry,String> {
}
