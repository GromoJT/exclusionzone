package pl.gromotj.exclusionzone.articlesAndTags;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pl.gromotj.exclusionzone.entity.Article.ArticleModel;
import pl.gromotj.exclusionzone.entity.Article.TagModel;
import pl.gromotj.exclusionzone.repository.IArticleRepository;
import pl.gromotj.exclusionzone.repository.ITagRepository;

import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class createArticlesWithManyTags_whenSave_theyGetByTagOkTest {
    @Mock
    private ITagRepository iTagRepository;
    @Mock
    private IArticleRepository iArticleRepository;

    @Test
    public void test_1(){
        TagModel tag = new TagModel("Testowy tag");
        when(iTagRepository.save(any(TagModel.class))).thenReturn(tag);
        iTagRepository.save(tag);

        ArticleModel article = new ArticleModel();
        article.setContent("XXX");
        article.setTags(Collections.singleton(tag));
        when(iArticleRepository.save(any(ArticleModel.class))).thenReturn(article);
        when(iArticleRepository.findByTags_Name(eq("Testowy tag"))).thenReturn(Collections.singletonList(article));

        iArticleRepository.save(article);

        List<ArticleModel> articles = iArticleRepository.findByTags_Name("Testowy tag");
        verify(iTagRepository).save(tag);
        verify(iArticleRepository).save(article);
        assertTrue(articles.size() == 1);
    }
}
