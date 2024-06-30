package pl.gromotj.exclusionzone.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gromotj.exclusionzone.dto.AddSubEntryDto;
import pl.gromotj.exclusionzone.dto.SubEntryDto;
import pl.gromotj.exclusionzone.entity.Entry;
import pl.gromotj.exclusionzone.entity.SubEntry;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.exception.ResourceNotFoundException;
import pl.gromotj.exclusionzone.repository.IEntryRepository;
import pl.gromotj.exclusionzone.repository.ISubEntryRepository;
import pl.gromotj.exclusionzone.repository.IZoneUserRepository;

@Component
public class SubEntryMapper {
    private static IEntryRepository entryRepository = null;
    private static IZoneUserRepository zoneUserRepository = null;
    @Autowired
    public SubEntryMapper(
            IZoneUserRepository zoneUserRepository,
            IEntryRepository entryRepository) {
        this.zoneUserRepository = zoneUserRepository;
        this.entryRepository = entryRepository;
    }


    public static SubEntry mapAddSubEntryToSubEntry(AddSubEntryDto addSubEntryDto) {
        ZoneUser author = zoneUserRepository.findById(addSubEntryDto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException(
                String.format("ZoneUser with given id: %s does not exist", addSubEntryDto.getAuthorId())));
        Entry entry = entryRepository.findById(addSubEntryDto.getRelatedEntryId()).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Entry with given id: %s does not exist", addSubEntryDto.getRelatedEntryId())));

        return new SubEntry(
                author,
                addSubEntryDto.getName(),
                entry,
                addSubEntryDto.getTags(),
                addSubEntryDto.getContent()
        );
    }

    public SubEntryDto mapToSubEntryDto(SubEntry sEntry) {
        return new SubEntryDto(
                sEntry.getId(),
                sEntry.getAuthor().getId(),
                sEntry.getName(),
                sEntry.getEntry().getId(),
                sEntry.getSecondaryTags(),
                sEntry.getContent(),
                sEntry.isRedacted(),
                sEntry.getCreatedAt(),
                sEntry.getLastModifiedAt()
        );
    }
}
