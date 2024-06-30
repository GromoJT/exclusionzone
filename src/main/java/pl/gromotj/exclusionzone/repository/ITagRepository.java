package pl.gromotj.exclusionzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromotj.exclusionzone.entity.Article.TagModel;
@Repository
public interface ITagRepository extends JpaRepository<TagModel,String> {
}
