package pl.gromotj.exclusionzone.service.imlp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gromotj.exclusionzone.dto.AddEntryDto;
import pl.gromotj.exclusionzone.dto.EntryDto;
import pl.gromotj.exclusionzone.entity.Entry;
import pl.gromotj.exclusionzone.exception.ResourceNotFoundException;
import pl.gromotj.exclusionzone.mapper.EntryMapper;
import pl.gromotj.exclusionzone.repository.IEntryRepository;
import pl.gromotj.exclusionzone.service.IEntryService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class EntryServiceImplementation implements IEntryService {
    @Autowired
    private IEntryRepository entryRepository;

    @Autowired
    private  EntryMapper entryMapper;
    @Override
    public EntryDto createEntry(AddEntryDto addEntryDto) {
        Entry entry = EntryMapper.mapToEntry(addEntryDto);
        entryRepository.save(entry);
        return EntryMapper.mapToEntryDto(entry);
    }

    @Override
    public List<EntryDto> getAllEntries() {
        List<Entry> entries = entryRepository.findAll();
        return entries.stream().map(e -> entryMapper.mapToEntryDto(e)).collect(Collectors.toList());
    }

    @Override
    public EntryDto getEntryById(String id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                        String.format("Entry with given id : %s, does not exists",id)));
        return EntryMapper.mapToEntryDto(entry);
    }


    public EntryDto retractEntryById(String id) {
        Entry entry = entryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Entry with given id : %s, does not exists",id)));
        entry.setRedacted(true);
        entry.setLastModifiedAt(ZonedDateTime.now());
        entryRepository.save(entry);
        return EntryMapper.mapToEntryDto(entry);
    }

    public EntryDto updateEntry(String id, AddEntryDto addEntryDto) {
        Entry entry = entryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Entry with given id : %s, does not exists",id)));
        if(entry.getAuthor().getId() != addEntryDto.getAuthorId()) throw new IllegalArgumentException(); //Trzeba zaimplementować dedykowany wyjątek
        entry.setName(addEntryDto.getName());
        entry.setType(addEntryDto.getType());
        entry.setShortDescription(addEntryDto.getShort_description());
        entry.setImagesList(addEntryDto.getImage_list());
        entry.setLastModifiedAt(ZonedDateTime.now());
        entryRepository.save(entry);
        return EntryMapper.mapToEntryDto(entry);
    }
}
