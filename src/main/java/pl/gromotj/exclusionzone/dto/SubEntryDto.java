package pl.gromotj.exclusionzone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import pl.gromotj.exclusionzone.entity.Entry;
import pl.gromotj.exclusionzone.entity.SecondaryTagsEnum;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SubEntryDto {
    private String id;
    private String authorId;
    private String name;
    private String relatedEntryId;
    private Set<SecondaryTagsEnum> tags = new HashSet<>();
    private String content;
    private boolean iSRedacted;
    private ZonedDateTime created_at;
    private ZonedDateTime lastModifiedAt;


}
