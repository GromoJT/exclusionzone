package pl.gromotj.exclusionzone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Entry")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",updatable = false,nullable = false,unique = true)
    private String id;
    @ManyToOne
    @JoinColumn(name = "author")
    private ZoneUser author;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private IndividualRecordType type;

    @Column(name = "name")
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @ElementCollection
    @Column(name = "image_list")
    private List<String> imagesList;


    @Column(name = "is_redacted",columnDefinition = "boolean default false")
    private boolean isRedacted = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entry")
    private Set<SubEntry> subEntries = new HashSet<>();
    @CreationTimestamp
    @Column(name="created_at",updatable = false,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;
    @CreationTimestamp
    @Column(name="modified_at",updatable = true,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime lastModifiedAt;

    public Entry(String name, ZoneUser author, IndividualRecordType type, String shortDescription, List<String> imageList) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.shortDescription = shortDescription;
        this.imagesList=imageList;
    }



}
