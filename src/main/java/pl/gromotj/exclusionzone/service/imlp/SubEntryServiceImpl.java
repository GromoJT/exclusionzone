package pl.gromotj.exclusionzone.service.imlp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gromotj.exclusionzone.dto.AddSubEntryDto;
import pl.gromotj.exclusionzone.dto.SubEntryDto;
import pl.gromotj.exclusionzone.entity.Entry;
import pl.gromotj.exclusionzone.entity.SubEntry;
import pl.gromotj.exclusionzone.exception.ResourceNotFoundException;
import pl.gromotj.exclusionzone.mapper.EntryMapper;
import pl.gromotj.exclusionzone.mapper.SubEntryMapper;
import pl.gromotj.exclusionzone.repository.IEntryRepository;
import pl.gromotj.exclusionzone.repository.ISubEntryRepository;
import pl.gromotj.exclusionzone.service.IEntryService;
import pl.gromotj.exclusionzone.service.ISubEntryService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubEntryServiceImpl implements ISubEntryService {
    @Autowired
    private ISubEntryRepository subEntryRepository;
    @Autowired
    private IEntryRepository entryRepository;

    @Autowired
    private SubEntryMapper subEntryMapper;
    @Override
    public SubEntryDto createSubEntry(AddSubEntryDto addSubEntryDto) {
        SubEntry sEntry = SubEntryMapper.mapAddSubEntryToSubEntry(addSubEntryDto);
        subEntryRepository.save(sEntry);
        return subEntryMapper.mapToSubEntryDto(sEntry);
    }
    @Override
    public List<SubEntryDto> getAllSubEntries() {
        List<SubEntry> subEntries = subEntryRepository.findAll();
        return subEntries.stream().map(e -> subEntryMapper.mapToSubEntryDto(e)).collect(Collectors.toList());
    }
    @Override
    public SubEntryDto getSubEntryById(String id) {
        SubEntry subEntry = subEntryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Entry with given id : %s, does not exists",id)));
        return subEntryMapper.mapToSubEntryDto(subEntry);
    }
    @Override
    public List<SubEntryDto> getAllEntriesOfEntry(String entryId) {
        List<SubEntry> subEntries = subEntryRepository.getAllSubEntriesRelatedToEntry(entryId);
        return subEntries.stream().map(e -> subEntryMapper.mapToSubEntryDto(e)).collect(Collectors.toList());
    }
}
