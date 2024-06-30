package pl.gromotj.exclusionzone.entity.Article;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags")
public class TagModel {

    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name="custom-uuid",
            strategy = "pl.gromotj.exclusionzone.functions.CustomUUIDGenerator",
            parameters = @org.hibernate.annotations.Parameter(name="prefix",value = "tags"))
    @Column(name = "id",updatable = false,nullable = false,unique = true)
    private String id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<ArticleModel> articles = new HashSet<>();

    public TagModel(String name){
        this.name = name;
    }
}
