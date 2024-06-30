package pl.gromotj.exclusionzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gromotj.exclusionzone.entity.Article.ArticleModel;

import java.util.List;
@Repository
public interface IArticleRepository extends JpaRepository<ArticleModel,String> {
    List<ArticleModel> findByTags_Name(String tagsName);
}
