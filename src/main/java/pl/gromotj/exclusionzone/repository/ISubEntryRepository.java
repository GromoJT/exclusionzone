package pl.gromotj.exclusionzone.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gromotj.exclusionzone.dto.SubEntryDto;
import pl.gromotj.exclusionzone.entity.SubEntry;

import java.util.List;

@Repository
@Transactional
public interface ISubEntryRepository extends JpaRepository<SubEntry,String> {
    @Query("SELECT s FROM SubEntry s WHERE s.entry.id = :entryId")
    List<SubEntry> getAllSubEntriesRelatedToEntry(@Param("entryId")String entryId);
}
