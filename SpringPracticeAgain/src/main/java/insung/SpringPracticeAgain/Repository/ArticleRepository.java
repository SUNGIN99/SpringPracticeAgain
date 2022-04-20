package insung.SpringPracticeAgain.Repository;

import org.springframework.data.repository.CrudRepository;
import insung.SpringPracticeAgain.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{

}