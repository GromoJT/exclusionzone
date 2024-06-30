package pl.gromotj.exclusionzone.entity.Article;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.gromotj.exclusionzone.entity.ZoneUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class ArticleModel {
    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name="custom-uuid",
            strategy = "pl.gromotj.exclusionzone.functions.CustomUUIDGenerator",
            parameters = @org.hibernate.annotations.Parameter(name="prefix",value = "rapo"))
    @Column(name = "id",updatable = false,nullable = false,unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name="author")
    private ZoneUser author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "many_articles_many_tags",
        joinColumns = @JoinColumn(name = "many_article_id",
        referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "many_tag_id",
            referencedColumnName = "id"))
    private Set<TagModel> tags = new HashSet<>();

    @Column(name = "content")
    private String content;
    @Column(name = "related_content")
    private Set<String> relatedContent = new HashSet<>();

}
