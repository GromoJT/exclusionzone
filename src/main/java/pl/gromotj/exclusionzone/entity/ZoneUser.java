package pl.gromotj.exclusionzone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


import pl.gromotj.exclusionzone.entity.Article.ArticleModel;

import pl.gromotj.exclusionzone.entity.Fraction.Character.CharacterModel;



import java.time.ZonedDateTime;
import java.util.Set;



@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zone_users")
public class ZoneUser{

//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name="custom-uuid",strategy = "pl.gromotj.exclusionzone.functions.CustomUUIDGenerator",
            parameters = @org.hibernate.annotations.Parameter(name="prefix",value = "zous"))
    @Column(name = "id",updatable = false,nullable = false,unique = true)
    private String id;

    @NotBlank
    @Size(min = 3,max = 35)
    @Column(name = "user_name",unique = true,nullable = false)
    private String userName;

    @NotBlank
    @Size(min = 2,max = 25)
    @Column(name = "lore_name",nullable = false)
    private String loreName;

    @NotBlank
    @Size(min = 2,max = 30)
    @Column(name = "lore_surname",nullable = false)
    private String loreSurname;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password",nullable = false)
    private String password;


    @Column(name = "global_role")
    private String globalRole;

    @Email
    @Column(name = "email",unique = true,nullable = false)
    private String email;


    @Column(name = "is_redacted",columnDefinition = "boolean default false")
    private boolean isRedacted = false;

    @CreationTimestamp
    @Column(name="created_at",updatable = false,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;

    @Column(name = "is_verified",columnDefinition = "boolean default false")
    private boolean isVerified = false;

    @OneToMany(mappedBy = "zoneUser",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<EmailConfirmationToken> vTokens;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CharacterModel> createdCharacters;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Entry> createdEntries;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<SubEntry> createdSubEntries;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ArticleModel> articles;

    public ZoneUser(String userName, String loreName, String loreSurname, String email, String globalRole, String password) {
        this.userName = userName;
        this.loreName = loreName;
        this.loreSurname = loreSurname;
        this.email = email;
        this.globalRole = globalRole;
        this.password = password;
    }
}
