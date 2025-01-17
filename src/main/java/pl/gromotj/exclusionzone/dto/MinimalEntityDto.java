package pl.gromotj.exclusionzone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MinimalEntityDto {
    private String id;
    private String name;
    private String shortDescription;
    private String imageId;
}
