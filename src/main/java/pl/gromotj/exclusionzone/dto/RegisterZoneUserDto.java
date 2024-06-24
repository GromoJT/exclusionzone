package pl.gromotj.exclusionzone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterZoneUserDto {

    private String userName;
    private String loreName;
    private String loreSurname;
    private String password;
    private String globalRole;
    private String email;

}
