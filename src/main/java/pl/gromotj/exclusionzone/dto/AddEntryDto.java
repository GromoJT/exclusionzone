package pl.gromotj.exclusionzone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gromotj.exclusionzone.entity.IndividualRecordType;
import pl.gromotj.exclusionzone.entity.ZoneUser;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddEntryDto {
    private String authorId;
    private String name;
    private IndividualRecordType type;
    private String short_description;
    private List<String> image_list;
}
