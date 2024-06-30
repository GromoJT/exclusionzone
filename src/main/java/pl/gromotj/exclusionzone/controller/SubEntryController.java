package pl.gromotj.exclusionzone.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gromotj.exclusionzone.dto.AddSubEntryDto;
import pl.gromotj.exclusionzone.dto.EntryDto;
import pl.gromotj.exclusionzone.dto.SubEntryDto;
import pl.gromotj.exclusionzone.service.imlp.SubEntryServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/subentry")
public class SubEntryController {

    @Autowired
    private SubEntryServiceImpl subEntryService;
    @PostMapping
    public ResponseEntity<String> addSubEntry(@RequestBody AddSubEntryDto addSubEntryDto){
        SubEntryDto saveSubEntry = subEntryService.createSubEntry(addSubEntryDto);
        return new ResponseEntity<>("SubEntry added", HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<SubEntryDto>> getAllEntries(){
        List<SubEntryDto> allSubEntries = subEntryService.getAllSubEntries();
        return new ResponseEntity<>(allSubEntries,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubEntryDto> getSubEntryById(@PathVariable("id") String id){
        SubEntryDto subEntry = subEntryService.getSubEntryById(id);
        return new ResponseEntity<>(subEntry,HttpStatus.OK);
    }
}
