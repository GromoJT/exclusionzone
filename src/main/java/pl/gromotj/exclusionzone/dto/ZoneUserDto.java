package pl.gromotj.exclusionzone.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import pl.gromotj.exclusionzone.entity.Anomaly.AnomalyModel;
import pl.gromotj.exclusionzone.entity.Artifact.ArtifactModel;
import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;
import pl.gromotj.exclusionzone.entity.Fraction.Character.CharacterModel;
import pl.gromotj.exclusionzone.entity.Region.Location.LocationModel;
import pl.gromotj.exclusionzone.entity.wildlife.WildlifeModel;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneUserDto{
    private String id;
    private String userName;
    private String loreName;
    private String loreSurname;
    private String password;
    private String globalRole;
    private String email;
    private boolean isRedacted;
    private ZonedDateTime createdAt;
    private boolean isVerified = false;
    private Set<EmailConfirmationToken> vTokens;
    private Set<ArtifactModel> createdArtifacts;
    private Set<AnomalyModel> createdAnomalies;
    private Set<LocationModel> createdLocations;
    private Set<CharacterModel> createdCharacters;
    private Set<WildlifeModel> createdWildlife;

}
