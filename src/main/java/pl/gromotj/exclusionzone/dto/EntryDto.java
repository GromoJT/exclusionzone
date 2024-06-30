package pl.gromotj.exclusionzone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gromotj.exclusionzone.entity.IndividualRecordType;
import pl.gromotj.exclusionzone.entity.SubEntry;
import pl.gromotj.exclusionzone.entity.ZoneUser;


import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryDto {
private String id;
private String authorId;
private String name;
private IndividualRecordType type;
private String shortDescription;
private List<String> imageList;
private  boolean isRedacted;
private Set<SubEntry> subEntrySet;
private ZonedDateTime created_at;
private ZonedDateTime lastModifiedAt;



}
