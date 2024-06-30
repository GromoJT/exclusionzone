package pl.gromotj.exclusionzone.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import pl.gromotj.exclusionzone.entity.Article.ArticleModel;
import pl.gromotj.exclusionzone.entity.EmailConfirmationToken;
import pl.gromotj.exclusionzone.entity.Entry;
import pl.gromotj.exclusionzone.entity.Fraction.Character.CharacterModel;
import pl.gromotj.exclusionzone.entity.SubEntry;


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


    private Set<CharacterModel> createdCharacters;
    private Set<Entry> createdEntry;
    private Set<SubEntry> createdSubEntry;
    private Set<ArticleModel> createdArticle;
}
