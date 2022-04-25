package insung.SpringPracticeAgain.Repository;

import org.springframework.data.repository.CrudRepository;
import insung.SpringPracticeAgain.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();

}
