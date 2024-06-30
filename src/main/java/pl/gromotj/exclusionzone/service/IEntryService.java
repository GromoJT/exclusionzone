package pl.gromotj.exclusionzone.service;

import pl.gromotj.exclusionzone.dto.AddEntryDto;
import pl.gromotj.exclusionzone.dto.EntryDto;
import pl.gromotj.exclusionzone.dto.RegisterZoneUserDto;
import pl.gromotj.exclusionzone.dto.ZoneUserDto;
import pl.gromotj.exclusionzone.entity.Entry;

import java.util.List;

public interface IEntryService {
    EntryDto createEntry(AddEntryDto addEntryDto);
    List<EntryDto> getAllEntries();

    EntryDto getEntryById(String id);
}
