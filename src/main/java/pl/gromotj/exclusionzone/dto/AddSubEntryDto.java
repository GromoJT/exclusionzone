package pl.gromotj.exclusionzone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import pl.gromotj.exclusionzone.entity.SecondaryTagsEnum;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSubEntryDto {
    private String authorId;
    private String name;
    private String relatedEntryId;
    private Set<SecondaryTagsEnum> tags = new HashSet<>();
    private String content;
}
