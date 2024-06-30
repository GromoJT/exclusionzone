package pl.gromotj.exclusionzone.mapper;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.gromotj.exclusionzone.dto.AddEntryDto;
import pl.gromotj.exclusionzone.dto.EntryDto;
import pl.gromotj.exclusionzone.entity.Entry;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.exception.ResourceNotFoundException;
import pl.gromotj.exclusionzone.repository.IZoneUserRepository;
@Component
public class EntryMapper {

    private static IZoneUserRepository zoneUserRepository;
    @Autowired
    public EntryMapper(IZoneUserRepository zoneUserRepository) {
        this.zoneUserRepository = zoneUserRepository;
    }
    public static EntryDto mapToEntryDto(Entry entry){
        return new EntryDto(
            entry.getId(),
            entry.getAuthor().getId(),
            entry.getName(),
                entry.getType(),
                entry.getShortDescription(),
                entry.getImagesList(),
                entry.isRedacted(),
                entry.getSubEntries(),
                entry.getCreatedAt(),
                entry.getLastModifiedAt()
        );
    }
    public static Entry mapToEntry(AddEntryDto addEntry){
        ZoneUser author = zoneUserRepository.findById(addEntry.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("ZoneUser with given id: %s does not exist", addEntry.getAuthorId())));
        return new Entry(
            addEntry.getName(),
                author,
            addEntry.getType(),
            addEntry.getShort_description(),
            addEntry.getImage_list()
        );
    }


    public static Entry mapEntryDtoToEntry (EntryDto entryDto){
        ZoneUser author = zoneUserRepository.findById(entryDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("ZoneUser with given id: %s does not exist", entryDto.getAuthorId())));
        return new Entry(
                entryDto.getId(),
                author,
                entryDto.getType(),
                entryDto.getName(),
                entryDto.getShortDescription(),
                entryDto.getImageList(),
                entryDto.isRedacted(),
                entryDto.getSubEntrySet(),
                entryDto.getCreated_at(),
                entryDto.getLastModifiedAt()
        );
    }
}
