package pl.gromotj.exclusionzone.service;

import pl.gromotj.exclusionzone.dto.AddSubEntryDto;
import pl.gromotj.exclusionzone.dto.EntryDto;
import pl.gromotj.exclusionzone.dto.SubEntryDto;

import java.util.List;

public interface ISubEntryService {
    SubEntryDto createSubEntry(AddSubEntryDto addSubEntryDto);
    List<SubEntryDto> getAllSubEntries();
    SubEntryDto getSubEntryById(String id);

    List<SubEntryDto> getAllEntriesOfEntry(String entryId);
}
