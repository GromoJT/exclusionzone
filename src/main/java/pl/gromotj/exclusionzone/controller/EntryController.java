package pl.gromotj.exclusionzone.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gromotj.exclusionzone.dto.AddEntryDto;
import pl.gromotj.exclusionzone.dto.EntryDto;
import pl.gromotj.exclusionzone.dto.SubEntryDto;
import pl.gromotj.exclusionzone.service.imlp.EntryServiceImplementation;
import pl.gromotj.exclusionzone.service.imlp.SubEntryServiceImpl;
import pl.gromotj.exclusionzone.service.imlp.ZoneUserServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/entry")
public class EntryController {
    @Autowired
    private EntryServiceImplementation entryServiceImplementation;
    @Autowired
    private ZoneUserServiceImpl zoneUserService;
    @Autowired
    private SubEntryServiceImpl subEntryService;
    @PostMapping()
    public ResponseEntity<String> addEntry(@RequestBody AddEntryDto addEntryDto){
        EntryDto saveEntry = entryServiceImplementation.createEntry(addEntryDto);
        //zoneUserService.addEntryToUser(saveEntry.getAuthorId(),saveEntry.getId());
        return new ResponseEntity<>("Entry added!", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EntryDto>> getAllEntries(){
        List<EntryDto> allEntries = entryServiceImplementation.getAllEntries();
        return new ResponseEntity<>(allEntries,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntryDto> getEntryById(@PathVariable("id") String id){
        EntryDto entry = entryServiceImplementation.getEntryById(id);
        return new ResponseEntity<>(entry,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> redactEntryById(@PathVariable("id") String id){
        entryServiceImplementation.retractEntryById(id);
        return new ResponseEntity<>("Entry succesfully Redacted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryDto> updateEntryById(@PathVariable("id") String id,@RequestBody AddEntryDto addEntryDto){
        EntryDto updatedEntry = entryServiceImplementation.updateEntry(id,addEntryDto);
        return new ResponseEntity<>(updatedEntry,HttpStatus.OK);
    }

    @GetMapping("/allsubentriesof/{entryId}")
    public ResponseEntity<List<SubEntryDto>> getAllRelatedSubEntries(@PathVariable("entryId") String entryId){
        List<SubEntryDto> allEntries = subEntryService.getAllEntriesOfEntry(entryId);
        return new ResponseEntity<>(allEntries,HttpStatus.OK);
    }

}
