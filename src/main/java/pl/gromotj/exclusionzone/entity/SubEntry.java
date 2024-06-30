package pl.gromotj.exclusionzone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;



import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Subentry")
public class SubEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",updatable = false,nullable = false,unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "author")
    private ZoneUser author;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id",nullable = false)
    private Entry entry;

//    @ElementCollection(targetClass = SecondaryTagsEnum.class)
//    @CollectionTable(name = "subentry_secondary_tags", joinColumns = @JoinColumn(name = "subentry_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "secondary_tags",nullable = false)
    private Set<SecondaryTagsEnum> secondaryTags = new HashSet<>();

    @Column(name = "content")
    private String content;

    @Column(name = "is_redacted",columnDefinition = "boolean default false")
    private boolean isRedacted = false;

    @CreationTimestamp
    @Column(name="created_at",updatable = false,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;

    @CreationTimestamp
    @Column(name="modified_at",updatable = true,columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime lastModifiedAt;

    public SubEntry(ZoneUser author, String name, Entry relatedEntry, Set<SecondaryTagsEnum> tags, String content) {
        this.author = author;
        this.name = name;
        this.entry = relatedEntry;
        this.secondaryTags=tags;
        this.content=content;
    }
}
